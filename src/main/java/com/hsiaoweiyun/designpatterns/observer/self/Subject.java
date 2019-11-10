package com.hsiaoweiyun.designpatterns.observer.self;

public interface Subject {

    void registryObserver(Observer observer);

    void unRegistryObserver(Observer observer);

    void notifyObservers(int num);

}
