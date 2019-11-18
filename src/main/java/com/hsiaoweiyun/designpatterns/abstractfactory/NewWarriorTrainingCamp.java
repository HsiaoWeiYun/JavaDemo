package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class NewWarriorTrainingCamp implements TrainingCamp {

    private EquipFactory equipFactory = new WarriorEquipFactory();


    @Override
    public NewAdventurer trainAdventurer() {

        NewAdventurer adventurer = new NewWarror();
        adventurer.setWeapon(equipFactory.productWeapon());
        adventurer.setClothes(equipFactory.productClothes());

        return adventurer;
    }
}
