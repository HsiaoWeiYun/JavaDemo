package com.hsiaoweiyun.designpatterns.observer.self;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Subject numSubject = new NumSubject();
        Observer observerA = new ObserverA();
        Observer observerB = new ObserverB();

        numSubject.registryObserver(observerA);
        numSubject.registryObserver(observerB);


        Thread t1 = new Thread(()->{
            numSubject.notifyObservers(1);
            numSubject.notifyObservers(2);
            numSubject.notifyObservers(3);
        });

        t1.start();

        t1.join();

        numSubject.unRegistryObserver(observerB);

        t1 = new Thread(()->{
            numSubject.notifyObservers(4);
            numSubject.notifyObservers(5);
        });

        t1.start();

        t1.join();

        System.out.println("FINISH");
    }

}
