package com.hsiaoweiyun.generic;

import org.junit.Assert;
import org.junit.Test;

public class GenericTest {

    @Test
    public void testGenericClass(){
        GenericClass<String> genericClass = new GenericClass<>();
        genericClass.add("A");
        genericClass.add("B");
        genericClass.add("C");
        genericClass.printAll();

        GenericClass2 genericClass2 = new GenericClass2();
        genericClass2.add(1);
        genericClass2.add(2);
        genericClass2.add(3);
        genericClass2.printAll();

        genericClass.showApple(new Apple());
        genericClass.showBanana(new Banana());
        genericClass.showFruit(new Apple());
        genericClass.showBanana(new Banana());
    }

    @Test
    public void testGenericMethod(){
        GenericClass genericClass = new GenericClass();
        genericClass.genericMethod("A");
        Assert.assertEquals("BB", genericClass.genericMethodAndReturn("BB"));
    }

    @Test
    public void testGenericExtends(){
        //Node<Fruit> fruitNode = new Node<Apple>(new Apple(), null);  編譯錯誤, Node<Apple> 不是一種 Node<Fruit>

        Node<Apple> appleNode = new Node<Apple>(new Apple(), null);  //編譯成功

        Node<? extends Fruit> fruitNode = new Node<Apple>(new Apple(), null);  //編譯成功 ?代表一種型態
        Object obj = fruitNode.value;  //只知道value會繼承Fruit 但是到底是apple還是banana並不知道
        Fruit f = fruitNode.value; //只知道value會繼承Fruit 但是到底是apple還是banana並不知道
        //Apple apple = fruitNode.value;  所以會錯
        //Banana banana = fruitNode.value;  所以會錯
        //結論: 因為java泛型只提供編譯時期的檢查, 由於無法確切在執行時期得知確切型態, 所以只能依據編譯時期的型態來做檢查

        //fruitNode.testParameter(new Fruit());   使用? extends型式的泛型作為參數會錯誤
        Fruit fruit = fruitNode.testReturn();  //但使用於回傳是可以的
        //結論: 使用? extends型式的泛型適合當作資料提供者
    }

    @Test
    public void testGenericSuper(){
        Node<? super Apple> fruitNode = new Node<Fruit>(new Fruit(), null);  //此時型態為Apple的父類或自己
        //Fruit fruit = fruitNode.value; 編譯不通過, 只知道是Apple 的父類或自己, 可能是 Apple Fruit Object
        //Apple apple = fruitNode.value; 編譯不通過
        Object obj = fruitNode.value; //通過
    }

}
