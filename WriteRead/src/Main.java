import java.io.*;
import java.util.Random;

/**
 * Created by Lenovo on 08.04.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Random random=new Random();

        int count=0;
        try(DataOutputStream in=new DataOutputStream(new FileOutputStream("WriteRead/src/in.bin"))){

            for(int i=0;i<100;i++){
                in.writeInt(random.nextInt(1000)+1);
            }
            in.close();

        }
        catch (IOException e){
            e.getStackTrace();
        }

        try(DataInputStream in=new DataInputStream(new FileInputStream("WriteRead/src/in.bin"))){

            try(DataOutputStream out=new DataOutputStream(new FileOutputStream("WriteRead/src/out.bin"))){
                for(int i=0;i<100;i++){
                    int a=in.readInt();
                    if(a%2!=0){
                        out.writeInt(a);
                        count++;
                    }
                }
                out.close();
            }
            catch (IOException e){
                e.getStackTrace();
            }
            in.close();
        }
        catch (IOException e){
            e.getStackTrace();
        }


/////////////Проверка
        try(DataInputStream in=new DataInputStream(new FileInputStream("WriteRead/src/out.bin"))) {
            for(int i=0;i<count;i++){
                System.out.println(in.readInt());
            }
            in.close();
        }
        catch (IOException e){
            e.getStackTrace();
        }

        System.out.println();
        System.out.println(count+" кол-во нечетных");
    }
}
