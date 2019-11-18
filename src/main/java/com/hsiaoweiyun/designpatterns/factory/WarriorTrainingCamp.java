package com.hsiaoweiyun.designpatterns.factory;

import com.hsiaoweiyun.designpatterns.simplefactory.Adventurer;
import com.hsiaoweiyun.designpatterns.simplefactory.Warrior;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class WarriorTrainingCamp implements TrainingCamp {
    @Override
    public Adventurer trainAdventurer() {
        return new Warrior();
    }
}