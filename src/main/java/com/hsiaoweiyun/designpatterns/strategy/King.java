package com.hsiaoweiyun.designpatterns.strategy;

/**
 * @author VictorHsiao on 2019/11/8.
 */
public class King extends Character {

    public King() {
        weaponBehavior = new SwordWeaponBehavior();
    }

    @Override
    public void fight() {
        System.out.println("king start fight");
        weaponBehavior.useWeapon();
    }


}
