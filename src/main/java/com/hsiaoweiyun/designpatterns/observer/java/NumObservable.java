package com.hsiaoweiyun.designpatterns.observer.java;

import java.util.Observable;

//Observable 是class, 缺點是要在繼承其他物件的話會比較困難
//優點是可以把數據推給觀察者, 觀察者除了接收數據外也可以自己用拉的.
public class NumObservable extends Observable {

    private int num;


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        setChanged();  //多一個changed 的flag, 優點是其實有可能不是每一次都要通知觀察者
        notifyObservers();
    }
}
