package com.hsiaoweiyun.java8;

import org.junit.Test;

public class DefaultTest {

    @Test
    public void test(){
        //預設直接繼承default
        TestDefault1 testDefault1 = new TestDefault2();
        testDefault1.foo1();
        testDefault1.foo2();
        TestDefault1.foo3();

        testDefault1 = new TestDefault3();
        testDefault1.foo2();
        TestDefault1.foo3();

        testDefault1 = new TestDefault5();
        testDefault1.foo1();  //abstract會自動蓋掉interface版本的
        testDefault1.foo2();  //abstract class 可在將default method 指定為abstract

        /*
        * default 與 abstract 差異
        * 接口可以被类多实现（被其他接口多继承），抽象类只能被单继承。
        * 接口中没有 this 指针，没有构造函数，不能拥有实例字段（实例变量）或实例方法，无法保存 状态（state），抽象方法中可以。
        * 抽象类不能在 java 8 的 lambda 表达式中使用。
        * 从设计理念上，接口反映的是 “like-a” 关系，抽象类反映的是 “is-a” 关系。
        * */


    }



}
