package ru.kpfu.itis.group11506.horse;

/**
 * Created by Lenovo on 06.05.2016.
 */


public class Horse implements Runnable {

    int count;
    static boolean first=true;
    public Horse(int count){
        this.count=count;
    }

    public void run() {

        int dist=10000;
        for(int i=0;i<dist;i++){
            if(i==dist-1){
                if(first==true) {
                    System.out.println("Первой финишировала/и "+count+" лошадь/и");
                }
            }
        }
        first=false;
    }


}
