package ru.kpfu.itis.group11506.parsing;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by Lenovo on 20.04.2016.
 */
public class Main {
    public static void main(String[] args) throws MyException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        try{

            String str=reader.readLine();

            if(str.charAt(0)=='-')
                System.out.println(MyParser.evalute("0"+str));
            else
                System.out.println(MyParser.evalute(str));
        }
        catch (MyException e){
            System.err.println("Неправильный ввод выражения: "+e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
}


