package com.hsiaoweiyun.designpatterns.observer.self;

public class ObserverB implements Observer, Displayable {

    private int number = 0;

    @Override
    public void update(int num) {
        number = num;
        displayNum();
    }

    @Override
    public void displayNum(int num) {
        System.out.println("ObserverB displayNum: " + num);
    }

    @Override
    public void displayNum() {
        System.out.println("ObserverB displayNum: " + number);
    }
}
