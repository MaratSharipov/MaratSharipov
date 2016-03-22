package ru.kpfu.itis.deliviry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserFactory {
    public static final int MAX_TRY=10;

    public static User createUser() throws UserInputException, IOException {
        Scanner scanner=new Scanner(System.in);
        ////////////////////////////////////////////////phone
        String inputNumber= null;
        boolean hasInputNumber=false;
        System.out.println("Введите ваш телефонный номер");
        for(int tryIndex=0;tryIndex<MAX_TRY && !hasInputNumber;tryIndex++) {
            try {
                inputNumber = getPhoneNumber(scanner);
                hasInputNumber=true;
            } catch (UserInputException exception) {
                System.err.println("Неправильный ввод телефонных данных: "+exception.getMessage());
            }
        }

        if(inputNumber==null){
            throw new UserInputException("Превышан количество попыток");
        }

        ////////////////////////////////////////////////////address

        System.out.println("Введите ваш адрес: г.Казань, ул.Астахова, д.15, кв.45");
        String inputAddress=null;
        boolean hasInputAddress=false;
        for(int tryIndex=0;tryIndex<MAX_TRY && !hasInputAddress; tryIndex++) {
            try {
                inputAddress = getInputAddress();
                hasInputAddress = true;
            }
            catch (UserInputException e){
                System.err.println("Неправильный ввод данных адреса: "+e.getMessage());
            }
        }

        if(inputAddress==null){
            throw new UserInputException("Превышан количество попыток");
        }


        ////////////////////////////////////////////Card
        System.out.println();
        System.out.println("Номер вашей кредитной карты");
        System.out.println("Пример ввода: ****-****-****-****");
        boolean hasInputCardNumb=false;
        String inputCardNumb=null;

        for(int tryIndex=0;tryIndex<MAX_TRY && !hasInputCardNumb;tryIndex++){
            try {
                inputCardNumb = getCardNumb(scanner);
                hasInputCardNumb=true;
            }
            catch (UserInputException e){
                System.err.println("Неправильный ввод данных карточки: "+e.getMessage());
            }
        }
        if(inputCardNumb==null)
            throw new UserInputException("Превышан количество попыток");

        ///////////////////////////////////////////////Name


        System.out.println("Введите ваше имя");
        boolean hasInputName=false;
        String inputName=null;

        for(int tryIndex=0;tryIndex<MAX_TRY && !hasInputName;tryIndex++){
            try{
                inputName=getName(scanner);
                hasInputName=true;
            }
            catch (UserInputException e){
                System.err.println("Неправильно введено имя: "+e.getMessage());
            }
        }
        if(inputName==null)
            throw new UserInputException("Превышан лимит попыток");

        //////////////////////////////////////////////////////////////////////
        return new User(inputName,inputNumber,inputAddress,inputCardNumb);
    }

    private static  String getPhoneNumber(Scanner scanner) throws UserInputException{

        char[] symbols="йцукенгшщзхъэждлорпавыфячсмитьбю!@#$%^&.-_,qwertyuiop[]asdfghjklzxcvbnm".toCharArray();
        String userInput = scanner.next().toLowerCase();

        char[] user=userInput.toCharArray();
        for(int i=0;i<user.length;i++){
            for(int j=0;j<symbols.length;j++){
                if(user[i]==symbols[j]){
                    throw new UserInputException("Номер должен содержать только цифры");
                }
            }
        }
        int inputSize=userInput.length();
        if(inputSize!=11 && inputSize!=7){
            throw new UserInputException("Неправильная длина номера");
        }
        return userInput;
    }

    private static String getCardNumb(Scanner scanner) throws UserInputException {

        char[] symbols = "йцукенгшщзхъэждлорпавыфячсмитьбю!@#$%^&._,qwertyuiop[]asdfghjklzxcvbnm".toCharArray();
        String userInputCard = scanner.next().toLowerCase();
        char[] user = userInputCard.toCharArray();

        for (int i = 0; i < user.length; i++) {
            for (int j = 0; j < symbols.length; j++) {
                if (user[i] == symbols[j]) {
                    throw new UserInputException("Номер карточки не может содержать буквы");

                }
            }
        }

        String[] cardArr=userInputCard.split("-");
        if(cardArr.length!=4)
            throw new UserInputException("Неправильно ввели данные");
        for(int i=0;i<cardArr.length;i++){
            if(cardArr[i].length()!=4)
                throw new UserInputException("Неправильно ввели данные");
        }
        return userInputCard;
    }


    private static String getName(Scanner scanner) throws UserInputException {
        char[] symbols="1234567890-!@#$%^&*()-+=.,".toCharArray();
        String userInputName=scanner.next().toLowerCase();
        for(int i=0;i<userInputName.length();i++){
            for(int j=0;j<symbols.length;j++){
                if(userInputName.charAt(i)==symbols[j]){
                    throw new UserInputException("Имя должен содержать только буквы");
                }
            }
        }
        if(userInputName.length()<2)
            throw new UserInputException("Имя должен содержать больше одной буквы");

        return userInputName;

    }

    private static String getInputAddress() throws UserInputException, IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String userInputAddress=reader.readLine().toLowerCase();


        String[] arr=userInputAddress.split(",");

        if(arr.length!=4)
            throw new UserInputException("Не корректные данные");

        for(int i=0;i<arr.length;i++){
            if(i==0){
                if(!((arr[i].charAt(0)=='г' || arr[i].charAt(0)=='с') && arr[i].charAt(1)=='.'))
                    throw new UserInputException("Неправильно город или село");
            }

            if(i==1){
                if(!(arr[i].charAt(1)=='у' && arr[i].charAt(2)=='л' && arr[i].charAt(3)=='.'))
                    throw new UserInputException("Неправильно написали улицу");
            }

            if(i==2){
                if(!(arr[i].charAt(1)=='д' && arr[i].charAt(2)=='.'))
                    throw new UserInputException("Неправильно написали номер дома");
            }

            if(i==3){
                if(!(arr[i].charAt(1)=='к' && arr[i].charAt(2)=='в' && arr[i].charAt(3)=='.'))
                    throw new UserInputException("Неправильно написли номер квартиры");
            }

        }

        return userInputAddress;
    }


}
