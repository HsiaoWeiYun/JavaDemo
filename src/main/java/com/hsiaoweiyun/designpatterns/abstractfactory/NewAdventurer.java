package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public abstract class NewAdventurer {
    protected Weapon weapon;
    protected Clothes clothes;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public abstract void display();
}
