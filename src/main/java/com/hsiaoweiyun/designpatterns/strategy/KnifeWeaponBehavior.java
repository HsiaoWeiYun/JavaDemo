package com.hsiaoweiyun.designpatterns.strategy;

/**
 * @author VictorHsiao on 2019/11/8.
 */
public class KnifeWeaponBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        System.out.println("use Knife Weapon");
    }
}
