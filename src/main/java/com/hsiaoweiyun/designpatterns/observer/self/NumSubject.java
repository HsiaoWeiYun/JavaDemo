package com.hsiaoweiyun.designpatterns.observer.self;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NumSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();


    @Override
    public void registryObserver(final Observer observer) {
        if (Objects.isNull(observer)) {
            return;
        }
        observers.add(observer);
    }

    @Override
    public void unRegistryObserver(final Observer observer) {
        if (Objects.isNull(observer)) {
            return;
        }
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int num) {
        observers.forEach(observer -> observer.update(num));
    }
}
