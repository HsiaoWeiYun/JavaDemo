package com.hsiaoweiyun.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @author VictorHsiao on 2019/11/11.
 */
public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;
    @Override
    public abstract BigDecimal getCost();

    @Override
    public abstract String getDescription();
}
