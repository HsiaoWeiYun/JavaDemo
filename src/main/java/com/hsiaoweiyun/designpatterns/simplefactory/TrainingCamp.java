package com.hsiaoweiyun.designpatterns.simplefactory;

import java.util.Objects;

/**
 * @author VictorHsiao on 2019/11/18.
 */
public class TrainingCamp {


    public Adventurer trainAdventurer(final String type) {

        Adventurer adventurer = null;

        switch (Objects.requireNonNull(type)) {
            case "Archer":
                adventurer = new Archer();
                break;
            case "Warrior":
                adventurer = new Warrior();
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return adventurer;
    }


}
