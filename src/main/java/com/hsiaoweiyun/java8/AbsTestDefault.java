package com.hsiaoweiyun.java8;

public abstract class AbsTestDefault implements TestDefault1 {

    public abstract void foo1();

    @Override
    public void foo2() {
        System.out.println("AbsTestDefault.foo2");
    }
}
