package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public abstract class Weapon {
    protected int atk;
    protected int range;

    public void display() {
        System.out.println(this.getClass().getSimpleName() + " atk = " + atk + " , range = " + range);
    }
}
