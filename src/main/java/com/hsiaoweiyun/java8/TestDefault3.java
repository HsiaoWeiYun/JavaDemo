package com.hsiaoweiyun.java8;

public class TestDefault3 implements TestDefault1, TestDefault4 {

    @Override
    public void foo1() {
        //繼承衝突, 需要Override
        TestDefault4.super.foo1();
    }

    @Override
    public void foo2() {
        System.out.println("TestDefault3.foo2");
    }
}
