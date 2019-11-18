package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public abstract class Clothes {

    protected int def;

    public void display() {
        System.out.println(this.getClass().getSimpleName() + " def = " + def);
    }
}
