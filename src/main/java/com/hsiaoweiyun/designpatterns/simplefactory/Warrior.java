package com.hsiaoweiyun.designpatterns.simplefactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class Warrior implements Adventurer {

    @Override
    public void attack() {
        System.out.println("鬥士纏上鬥氣, 用長劍進行攻擊");
    }
}
