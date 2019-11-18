package com.hsiaoweiyun.designpatterns.abstractfactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class Test {

    public static void main(String[] args) {

        //抽象工廠模式, 是在工廠模式上多強調抽象關係, 像是武器與衣服這類的東西都是屬於冒險者的裝備, 所以他們多了這一層抽象關係.

        TrainingCamp warriorTrainingCamp = new NewWarriorTrainingCamp();
        TrainingCamp archerTrainingCamp = new NewArcherTrainingCamp();

        NewAdventurer adventurer1 = warriorTrainingCamp.trainAdventurer();
        NewAdventurer adventurer2 = archerTrainingCamp.trainAdventurer();

        adventurer1.display();
        adventurer2.display();
    }
}
