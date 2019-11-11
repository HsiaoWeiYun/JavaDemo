package com.hsiaoweiyun.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @author VictorHsiao on 2019/11/11.
 */
public class Milk extends CondimentDecorator {


    public Milk(Beverage beverage) {
        this.beverage = beverage;
        cost = new BigDecimal("20");
        description = "牛奶";
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
