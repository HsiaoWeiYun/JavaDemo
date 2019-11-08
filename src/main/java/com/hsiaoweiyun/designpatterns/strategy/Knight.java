package com.hsiaoweiyun.designpatterns.strategy;

/**
 * @author VictorHsiao on 2019/11/8.
 */
public class Knight extends Character {

    public Knight(){
        weaponBehavior = new AxeWeaponBehavior();
    }

    @Override
    public void fight() {
        System.out.println("fight start fight");
        weaponBehavior.useWeapon();
    }
}
