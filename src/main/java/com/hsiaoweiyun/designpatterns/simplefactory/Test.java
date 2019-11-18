package com.hsiaoweiyun.designpatterns.simplefactory;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class Test {

    public static void main(String[] args) {

        //將產生物件邏輯封裝起來成為一個工廠, 就是簡單工廠模式,
        //若要修改產生物件邏輯則需要修改工廠, 違反開放封閉原則


        TrainingCamp trainingCamp = new TrainingCamp();

        Adventurer adventurer1 = trainingCamp.trainAdventurer("Archer");
        Adventurer adventurer2 = trainingCamp.trainAdventurer("Warrior");

        adventurer1.attack();
        adventurer2.attack();

    }
}