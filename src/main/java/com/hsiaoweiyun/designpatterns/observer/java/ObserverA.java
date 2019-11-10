package com.hsiaoweiyun.designpatterns.observer.java;

import com.hsiaoweiyun.designpatterns.observer.self.Displayable;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class ObserverA implements Observer, Displayable {

    private int number = 0;

    @Override
    public void update(Observable o, Object arg) {
        if(Objects.nonNull(o) && o instanceof NumObservable){
            NumObservable numObservable = (NumObservable) o;
            displayNum(numObservable.getNum());  //用拉的
            return;
        }

        if(Objects.nonNull(arg) && arg instanceof Integer){
            number = (int) arg;
        }
    }

    @Override
    public void displayNum(int num) {
        System.out.println("ObserverA displayNum: " + num);
    }

    @Override
    public void displayNum() {
        System.out.println("ObserverA displayNum: " + number);
    }
}
