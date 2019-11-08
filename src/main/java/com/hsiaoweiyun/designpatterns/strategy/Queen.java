package com.hsiaoweiyun.designpatterns.strategy;

/**
 * @author VictorHsiao on 2019/11/8.
 */
public class Queen extends Character {

    public Queen(){
        weaponBehavior = new BowAndArrowBehavior();
    }

    @Override
    public void fight() {
        System.out.println("Queen start fight");
        weaponBehavior.useWeapon();
    }
}
