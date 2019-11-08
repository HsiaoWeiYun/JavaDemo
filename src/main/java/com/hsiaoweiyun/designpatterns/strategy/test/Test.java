package com.hsiaoweiyun.designpatterns.strategy.test;

import com.hsiaoweiyun.designpatterns.strategy.King;
import com.hsiaoweiyun.designpatterns.strategy.KnifeWeaponBehavior;
import com.hsiaoweiyun.designpatterns.strategy.Knight;
import com.hsiaoweiyun.designpatterns.strategy.Queen;

/**
 * @author VictorHsiao on 2019/11/8.
 */
public class Test {

    public static void main(String[] args){
        King king = new King();
        king.fight();
        Queen queen = new Queen();
        queen.fight();
        Knight knight = new Knight();
        knight.setWeaponBehavior(new KnifeWeaponBehavior());
        knight.fight();
    }
}
