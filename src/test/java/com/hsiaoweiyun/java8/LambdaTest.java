package com.hsiaoweiyun.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaTest {

    @Test
    public void simpleLambda(){

        //舊的寫法長這樣, 當interface只有一個方法需要實作時可以用Lambda語法,
        //其實我們在舊有寫法只關心匿名類別內部那個需要實作的方法裡面的輸入 實作 輸入,
        //所以Lambda讓語意變得更清晰, 效能也變得更好,
        //不需要new一個新的匿名class出來而是類似call function的方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("simpleLambda");
            }
        }).start();

        //最簡單的Lambda表示式, 輸入->匿名類別內部實作
        new Thread(
                ()->{
                    System.out.println("simpleLambda");
                }
                ).start();

        String[] names = {"Victor", "Tom", "Willson"};
        Arrays.sort(names, (o1,o2)-> {return o1.length()- o2.length();});   //Lambda
        System.out.println(Arrays.toString(names));

        Arrays.sort(names, new Comparator<String>() {   //舊寫法
            @Override
            public int compare(String o1, String o2) {
                return o2.length()- o1.length();
            }
        });
        System.out.println(Arrays.toString(names));
    }


}
