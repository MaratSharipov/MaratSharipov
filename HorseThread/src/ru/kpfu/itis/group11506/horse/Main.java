package ru.kpfu.itis.group11506.horse;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 06.05.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ArrayList<Thread> horseList=new ArrayList<>(10);
        for(int i=0;i<10;i++){
            horseList.add(new Thread(new Horse(i+1)));
        }
        for(Thread a:horseList){
            a.start();
        }
    }

}


