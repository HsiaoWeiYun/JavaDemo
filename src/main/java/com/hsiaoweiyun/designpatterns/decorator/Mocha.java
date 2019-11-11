package com.hsiaoweiyun.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @author VictorHsiao on 2019/11/11.
 */
public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
        cost = new BigDecimal("10");
        description = "摩卡";
    }

    @Override
    public BigDecimal getCost() {
        return beverage.getCost().add(cost);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , " + description;
    }
}
