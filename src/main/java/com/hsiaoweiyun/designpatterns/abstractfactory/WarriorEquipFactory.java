package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class WarriorEquipFactory implements EquipFactory {

    @Override
    public Clothes productClothes() {
        return new Armor();
    }

    @Override
    public Weapon productWeapon() {
        return new LongSword();
    }
}
