package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class NewArcher extends NewAdventurer {

    @Override
    public void display() {
        System.out.println("weapon: atk= " + weapon.atk + " range= " + weapon.range + " clothes: def= " + clothes.def);
    }
}
