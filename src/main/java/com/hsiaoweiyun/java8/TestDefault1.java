package com.hsiaoweiyun.java8;

public interface TestDefault1 {


    default void foo1(){
        System.out.println("TestDefault1.foo1");
    }

    default void foo2(){
        System.out.println("TestDefault1.foo2");
    }

    static void foo3(){
        System.out.println("TestDefault1.foo3");
    }


}
