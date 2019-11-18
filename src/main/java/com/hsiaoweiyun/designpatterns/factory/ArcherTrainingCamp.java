package com.hsiaoweiyun.designpatterns.factory;

import com.hsiaoweiyun.designpatterns.simplefactory.Adventurer;
import com.hsiaoweiyun.designpatterns.simplefactory.Archer;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class ArcherTrainingCamp implements TrainingCamp {

    @Override
    public Adventurer trainAdventurer() {
        return new Archer();
    }
}
