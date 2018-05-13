package com.hsiaoweiyun.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericClass<T> {

    private List<T> mList = new ArrayList<>();


    public void add(T t) {
        mList.add(t);
    }

    public void remove(T t) {
        mList.remove(t);
    }

    public void printAll() {
        mList.forEach(System.out::println);
    }

    //把此處的<K>視為宣告, 宣告此方法為泛型方法
    public <K> void genericMethod(K k){
        System.out.println(k.toString());
    }

    public <K> K genericMethodAndReturn(K k){
        return k;
    }

    public <F extends Apple> void showApple(F f){
        f.show();
    }

    public <F extends Banana> void showBanana(F f){
        f.show();
    }

    public <F extends Fruit> void showFruit(F f){
        f.show();
    }
}
