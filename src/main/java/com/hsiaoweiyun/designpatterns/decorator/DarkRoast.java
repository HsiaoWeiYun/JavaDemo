package com.hsiaoweiyun.designpatterns.decorator;

import java.math.BigDecimal;

/**
 * @author VictorHsiao on 2019/11/11.
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        cost = new BigDecimal("100");
        description = "深度烘焙咖啡";
    }
}
