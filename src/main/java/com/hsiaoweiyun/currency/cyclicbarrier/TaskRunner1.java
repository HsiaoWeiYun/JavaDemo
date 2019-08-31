package com.hsiaoweiyun.currency.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class TaskRunner1 {

    public static void main(String[] args) throws InterruptedException {

        int amount = 6;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(amount);
        CountDownLatch countDownLatch = new CountDownLatch(amount);

        for (int i = 0; i < amount; i++) {
            new Thread(new Task1(cyclicBarrier, countDownLatch)).start();
        }


        countDownLatch.await();


    }
}
