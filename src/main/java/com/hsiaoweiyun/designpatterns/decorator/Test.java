package com.hsiaoweiyun.designpatterns.decorator;

/**
 * @author VictorHsiao on 2019/11/11.
 */
public class Test {

    //decorator 模式, 優點: 可以動態的為物件添加功能, 比如java InputStream FileInputStream. 缺點: 會產生很多小物件
    public static void main(String[] args){
        Beverage espresso = new Espresso();
        espresso = new Milk(espresso);   //espresso 加 牛奶
        espresso = new Mocha(espresso);  //在加上一點摩卡

        System.out.println(espresso.getDescription());
        System.out.println(espresso.getCost().toPlainString());
    }

}
