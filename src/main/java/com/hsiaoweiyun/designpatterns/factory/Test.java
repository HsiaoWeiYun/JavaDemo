package com.hsiaoweiyun.designpatterns.factory;

import com.hsiaoweiyun.designpatterns.simplefactory.Adventurer;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class Test {

    public static void main(String[] args) {

        //工廠模式, 就是將工廠概念升級, 產生工廠概念的實體工廠.

        TrainingCamp archerTrainingCamp = new ArcherTrainingCamp();
        TrainingCamp warriorTrainingCamp = new WarriorTrainingCamp();

        Adventurer adventurer1 = archerTrainingCamp.trainAdventurer();
        Adventurer adventurer2 = warriorTrainingCamp.trainAdventurer();

        adventurer1.attack();
        adventurer2.attack();
    }

}
