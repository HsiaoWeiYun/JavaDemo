package com.hsiaoweiyun.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @author VictorHsiao on 2019/11/11.
 */
public abstract class Beverage {
    BigDecimal cost;
    String description;

    public BigDecimal getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
