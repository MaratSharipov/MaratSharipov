import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 19.05.2016.
 */
public class Func {

    String input;
    String output;
    String mask;
    boolean subFolders;
    boolean autoDelete;
    long interval;

    //метод "scan" принимает строку, и присваивает значения переменным класса
    //перехватыватся исключения или ошибки которые можно допустить при введении строки
    public void scan(String str) throws Exception {
        int count = 0;
        char[] banSymbols = "?/\\:<>|".toCharArray();
        String scanStr = "";
        String[] list = str.split(" -");
        for (int i = 0; i < list.length; i++) {
            scanStr = list[0];
            String[] list2 = list[i].split(" ");
            if(list2.length>2){
                throw new MyException("extra commands");
            }
            for (int j = 0; j < list2.length; j++) {
                if (list2[0].equals("input")) {
                    count++;
                    boolean fileExist = new File(list2[1]).exists();
                    if (!fileExist) {
                        throw new MyException("Input file is not found");
                    }
                    this.input = list2[1];
                }
                if (list2[0].equals("output")) {
                    count++;
                    boolean fileExist = new File(list2[1]).exists();
                    if (!fileExist) {
                        throw new MyException("Output file is not found");
                    }
                    this.output = list2[1];
                }
                if (list2[0].equals("mask")) {
                    count++;
                    for (Character symb : banSymbols) {
                        if (list2[1].contains(symb.toString())) {
                            throw new MyException("Illegal characters");
                        }
                    }
                    this.mask = list2[1].replace("*", ".*");
                }
                if (list2[0].equals("includeSubfolders")) {
                    count++;
                    if (!(list2[1].equals("true") || list2[1].equals("false"))) {
                        throw new MyException("Must be true or false(includeSubfolders)");
                    }
                    this.subFolders = Boolean.parseBoolean(list2[1]);
                }
                if (list2[0].equals("autoDelete")) {
                    count++;
                    if (!(list2[1].equals("true") || list2[1].equals("false"))) {
                        throw new MyException("Must be true or false(autoDelete)");
                    }
                    this.autoDelete = Boolean.parseBoolean(list2[1]);
                }
                if (list2[0].equals("waitInterval")) {
                    count++;
                    if (Integer.parseInt(list2[1]) < 0) {
                        throw new MyException("Must be positive int value");
                    }
                    interval = Long.parseLong(list2[1]) * 1000;
                }
            }
        }
        if(list.length>7){
            throw new MyException("extra commands");
        }

        if (count / 2 != 6) {
            throw new MyException("error in name of commands");
        }
        if (scanStr.equals("scan")) {
            runner();
            System.out.println("Successful");
        } else
            throw new MyException("error command of \"scan\"");
    }

    //из метода "runner" запускается сканер с изначальными путями
    public void runner() throws IOException, InterruptedException {
        Thread.sleep(interval); //Интервал(Не уверен что правильно понял задание команды)
        String mask = this.mask;
        String isStr = this.input + "\\";
        String outStr = this.output + "\\";
        File is = new File(isStr);
        folderMove(isStr, outStr, is.listFiles(), mask);
        del(outStr, outStr);
    }

    //данный метод ищет файлы которые подходят под маску и копирует(copyFile) их в выходную директорию
    //если файл является папкой, то метод пропускает его или идет в него рекурсивно(если subFolders=true)
    //метод удаляет копированные файлы из входной директории если autoDelete=true
    public void folderMove(String fileIn, String fileOut, File[] list, String maskStr) throws IOException {
        for (int i = 0; i < list.length; i++) {
            if (list[i].isFile()) {
                Pattern pat = Pattern.compile(maskStr);
                Matcher matcher = pat.matcher(list[i].getName());
                if (matcher.matches()) {
                    copyFile(fileIn + list[i].getName(), fileOut + list[i].getName());
                    if (autoDelete) { //проверка условий, удалять ли скопированные файлы
                        list[i].delete();
                    }
                }
            }
            if (!subFolders) { //проверка условий, проверять ли поддерикторий
                continue;
            }
            if (list[i].isDirectory()) {
                String fileStrIn = fileIn + list[i].getName() + "\\";
                File file = new File(fileStrIn);

                String fileStrOut = fileOut + list[i].getName() + "\\";
                File fileDir = new File(fileStrOut);
                fileDir.mkdir();

                folderMove(fileStrIn, fileStrOut, file.listFiles(), maskStr);
            }
        }
    }

    //Метод удаляет из выходной директории папки которые являются пустыми
    public void del(String currentFile, String original) {
        String dir = currentFile;
        File name = new File(dir);
        File[] file = new File(dir).listFiles();
        File origin = new File(original);
        if (origin.listFiles().length == 0) {
            return;
        }

        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) {
                del(dir + "\\" + file[i].getName(), original);
            }
        }
        if (file.length == 0) {
            name.delete();
            del(name.getParent(), original);
        }
    }
    //Метод "copyFile" копирует файл из source и вставляет его в dest
    private void copyFile(String source, String dest) throws IOException {
        InputStream is = null;
        OutputStream out = null;
        try {
            is = new FileInputStream(source);
            out = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } finally {
            is.close();
            out.close();
        }
    }
}
