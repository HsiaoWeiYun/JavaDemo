package com.hsiaoweiyun.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @author VictorHsiao on 2019/11/11.
 */
public class Espresso extends Beverage {

    public Espresso() {
        cost = new BigDecimal("90");
        description = "義式濃縮咖啡";
    }
}
