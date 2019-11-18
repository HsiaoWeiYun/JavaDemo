package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public interface EquipFactory {
    Clothes productClothes();

    Weapon productWeapon();
}