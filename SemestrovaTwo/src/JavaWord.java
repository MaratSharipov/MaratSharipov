import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.*;



/**
 * Created by Lenovo on 11.05.2016.
 */
public class JavaWord {
    public static void main(String[] args) {

        try {
            DataOutputStream stream=new DataOutputStream(new FileOutputStream("SemestrovaTwo/src/word.docx"));
            XWPFDocument a=new XWPFDocument();
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

            XWPFParagraph textData=a.createParagraph();
            int i=0;
            String operand="";
            boolean localB=false;
            boolean localI=false;
            boolean localMk=false;
            String str=reader.readLine()+" ";
            while(i!=str.length()){
                if(str.charAt(i)=='<'){
                    operand="";
                    while (str.charAt(i)!='>'){
                        operand+=String.valueOf(str.charAt(i));

                        i++;
                    }
                    operand+=">";
                    i++;

                }

                XWPFRun runs=textData.createRun();
                if(operand.equals("<b>")){
                    localB=true;
                }
                if(operand.equals("</b>")){
                    localB=false;
                }

                if(operand.equals("<i>")) {
                    localI=true;
                }

                if(operand.equals("</i>")){
                    localI=false;
                }

                if(operand.equals("<mk>")){
                    localMk=true;
                }
                if(operand.equals("</mk>")){
                    localMk=false;
                }

                runs.setText(String.valueOf(str.charAt(i)));

                runs.setItalic(localI);
                runs.setBold(localB);
                if(localMk){
                    runs.setUnderline(UnderlinePatterns.SINGLE);
                }

                i++;
            }

            a.write(stream);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //hello <b>world <i>how are you <mk>wherer are you</i> yoggouuu how are you</mk> where are you man</b> hello man <i>easy</i>

}
