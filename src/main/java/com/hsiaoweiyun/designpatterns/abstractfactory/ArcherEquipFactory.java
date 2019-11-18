package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class ArcherEquipFactory implements EquipFactory {

    @Override
    public Clothes productClothes() {
        return new Leather();
    }

    @Override
    public Weapon productWeapon() {
        return new Bow();
    }
}
