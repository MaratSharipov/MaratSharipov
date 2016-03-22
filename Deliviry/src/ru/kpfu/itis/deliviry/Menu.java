package ru.kpfu.itis.deliviry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Menu {

    private static ArrayList<String> menuList= new ArrayList<String>();
    private static ArrayList<Integer> menuItem=new ArrayList<Integer>();
    private static int sum=0;


    public static void menuShow()  {

        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Завершение выбора меню - 0");

        System.out.println("пицца \"Румянцев\" - 1 (600)");
        System.out.println("пицца \"Маргарита\" - 2 (550");

        System.out.println("ролл \"Снежный\" - 3 (320)");
        System.out.println("ролл \"Филадельфия\" - 4 (490)");

        System.out.println("Кока-кола - 5 (120)");
        System.out.println("Пиво - 6 (180)");

        System.out.println();
        int i=-1;
        int count=0;
        while(i!=0){


            try{
                i = Integer.parseInt(reader.readLine());
            }
            catch (Exception e){
                count++;
                System.out.println("Введите числовые данные "+e.getMessage());
                if(count == 10) {
                    System.out.println("Превышан лимит");
                    break;
                }
                else
                    continue;
            }







            switch(i){
                case 0:
                    break;
                case 1: menuList.add("Румянцев");
                    menuItem.add(600);
                    sum+=600;
                    break;
                case 2: menuList.add("Маргарита");
                    menuItem.add(550);
                    sum+=550;
                    break;

                case 3: menuList.add("Снежный");
                    menuItem.add(320);
                    sum+=320;
                    break;
                case 4: menuList.add("Филадельфия");
                    menuItem.add(490);
                    sum+=490;
                    break;

                case 5: menuList.add("Кока-кола");
                    menuItem.add(120);
                    sum+=120;
                    break;
                case 6: menuList.add("Пиво");
                    menuItem.add(180);
                    sum+=180;
                    break;
                default:
                    System.out.println("Такого нет");


            }
        }
    }


    public static void printOrder(){
        System.out.println();
        System.out.println("Ваш заказ");
        for(int i=0;i<menuList.size();i++){
            System.out.println((i+1)+") "+ menuList.get(i)+" - "+menuItem.get(i));
        }
        System.out.println("Общая сумма заказа составляет "+sum+" руб.");

    }

}
