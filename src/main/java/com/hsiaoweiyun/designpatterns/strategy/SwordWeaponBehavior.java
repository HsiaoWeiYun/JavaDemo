package com.hsiaoweiyun.designpatterns.strategy;

/**
 * @author VictorHsiao on 2019/11/8.
 */
public class SwordWeaponBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("use sword weapon");
    }
}
