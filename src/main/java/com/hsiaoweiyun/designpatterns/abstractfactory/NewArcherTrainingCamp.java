package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class NewArcherTrainingCamp implements TrainingCamp {

    private EquipFactory equipFactory = new ArcherEquipFactory();

    @Override
    public NewAdventurer trainAdventurer() {
        NewAdventurer adventurer = new NewArcher();
        adventurer.setClothes(equipFactory.productClothes());
        adventurer.setWeapon(equipFactory.productWeapon());
        return adventurer;
    }
}
