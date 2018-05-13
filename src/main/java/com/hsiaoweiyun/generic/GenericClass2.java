package com.hsiaoweiyun.generic;

import java.util.ArrayList;
import java.util.List;

//泛型物件實作泛型interface
public class GenericClass2 implements GenericInterface<Integer> {

    private List<Integer> mList = new ArrayList<>();


    public void add(Integer i){
        mList.add(i);
    }

    public void remove(Integer i){
        mList.remove(i);
    }

    public void printAll(){
        mList.forEach(System.out::println);
    }
}
