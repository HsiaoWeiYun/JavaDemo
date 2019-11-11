package com.hsiaoweiyun.designpatterns.decorator;

/**
 * @author VictorHsiao on 2019/11/11.
 */
public class Test {

    public static void main(String[] args){
        Beverage espresso = new Espresso();
        espresso = new Milk(espresso);   //espresso 加 牛奶
        espresso = new Mocha(espresso);  //在加上一點摩卡

        System.out.println(espresso.getDescription());
        System.out.println(espresso.getCost().toPlainString());
    }

}
