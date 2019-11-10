package com.hsiaoweiyun.designpatterns.observer.java;

public class Test {
    public static void main(String[] args){
        NumObservable numObservable = new NumObservable();
        numObservable.addObserver(new ObserverA());
        numObservable.setNum(1);
    }
}
