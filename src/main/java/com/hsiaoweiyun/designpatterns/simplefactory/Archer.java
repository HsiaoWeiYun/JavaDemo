package com.hsiaoweiyun.designpatterns.simplefactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class Archer implements Adventurer{

    @Override
    public void attack() {
        System.out.println("弓箭手使用弓箭進行攻擊");
    }
}
