package ru.kpfu.itis.group11506.parsing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lenovo on 03.05.2016.
 */
public class MyParser {

    private static boolean isOperator(char a){
        return a == '+' || a == '-' || a == '*' || a == '/';
    }

    private static int priority(char operator){
        if(operator=='*' || operator=='/')
            return 1;
        else if(operator=='+' || operator=='-')
            return 0;
        else
            return -1;
    }

    private static void compute(LinkedList<Double> list, char operator) throws MyException {
        double firstNum=list.removeLast();
        double secondNum=list.removeLast();

        switch(operator){
            case '+':
                list.add(secondNum+firstNum);
                break;
            case '-':
                list.add(secondNum-firstNum);
                break;
            case '*':
                list.add(secondNum*firstNum);
                break;
            case '/':
                list.add(secondNum/firstNum);
                break;
            default:
                throw new MyException("Ошибка");
        }
    }

    public static Double evalute(String str) throws MyException {
        exceps(str);

        LinkedList<Double> someNums = new LinkedList<>();
        LinkedList<Character> someOperators = new LinkedList<>();

        for(int i=0;i<str.length();i++){

            char a=str.charAt(i);
            if(a=='('){
                someOperators.add('(');
            }
            else if (a == ')') {
                while(someOperators.getLast()!='('){
                    compute(someNums, someOperators.removeLast());
                }
                someOperators.removeLast();
            }
            else if(isOperator(a)){
                while(!someOperators.isEmpty() && priority(someOperators.getLast())>=priority(a)){
                    compute(someNums,someOperators.removeLast());
                }
                someOperators.add(a);
            }
            else{

                String operand = "";
                while(i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i)=='.')) {

                    operand += str.charAt(i++);
                }

                --i;
                someNums.add(Double.parseDouble(operand));
            }
        }
        while(!someOperators.isEmpty()){
            compute(someNums, someOperators.removeLast());
        }

        return someNums.get(0);
    }

    private static void exceps(String str) throws MyException {

        List<Character> symbols=new ArrayList<Character>();
        symbols.add('+');
        symbols.add('-');
        symbols.add('*');
        symbols.add('/');
        int a=0, b=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='(')
                a++;
            if(str.charAt(i)==')')
                b++;
        }
        if(a!=b)
            throw new MyException("Не правильноe количество скобок");


        for(int i=0;i<str.length();i++){
            if(symbols.contains(str.charAt(i)) && symbols.contains(str.charAt(i+1))){
                throw new MyException("Подряд несколько оперцаий");
            }
        }
        if(symbols.contains(str.charAt(0)) && symbols.contains(str.charAt(str.length()))){
           throw new MyException("Знаки оперции не могут стоять первым или последним");
        }

        char[] sym="qwertyuiopasdfghjklzxcvbnmйцукенгшщзхъфывапролджэячсмитьбю".toCharArray();
        for(int i=0;i<sym.length;i++){
            for(int j=0;j<str.length();j++){
                if(sym[i]==str.charAt(j))
                    throw new MyException("Выражение не может сожержать буквы");
            }
        }

    }





}
