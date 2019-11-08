package com.hsiaoweiyun.designpatterns.strategy;

/**
 * @author VictorHsiao on 2019/11/8.
 */
public abstract class Character {

    protected WeaponBehavior weaponBehavior;

    abstract public void fight();

    public WeaponBehavior getWeaponBehavior() {
        return weaponBehavior;
    }

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }
}
