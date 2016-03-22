package ru.kpfu.itis.deliviry;

import java.io.IOException;
import java.util.ArrayList;

public class FoodDeliviry {

    public static void main(String[] args) throws IOException {

        ArrayList<User> userList=new ArrayList<User>();
        for(int i=0;i<2;i++) {
            Menu.menuShow();
            try {
                userList.add(UserFactory.createUser());

                System.out.println("Данные успешно ввидены");
                Menu.printOrder();
            } catch (UserInputException e) {
                System.err.println("Ошибка пользовательского ввода: " + e.getMessage());
                e.printStackTrace();
            }
        }

        for(User a:userList){
            System.out.println(a.toString());
        }


    }

}
