import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Lenovo on 19.05.2016.
 */
public class Scan {
    public static void main(String[] args) throws Exception {
        while (true) {
            try {
                //scan -input "JetBr\src\input" -output "JetBr\src\output" -mask "*file*.txt" -includeSubfolders true -autoDelete false -waitInterval 2
                //Отсюда программа начинает свое путешествие
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String oldStr = reader.readLine();
                if (oldStr.equals("exit"))
                    break;
                String str = oldStr.replace("\"", "");
                Func func = new Func();
                func.scan(str);

            } catch (MyException e) {
                e.printStackTrace();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}