package com.hsiaoweiyun.currency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Task1 implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private CountDownLatch countDownLatch;

    public Task1(CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch) {
        this.cyclicBarrier = cyclicBarrier;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.printf("\n%s start perform task", Thread.currentThread().getName());
            Thread.sleep(new Random().nextInt(2000));
            System.out.printf("\n%s finish, wait for other thread", Thread.currentThread().getName());
            cyclicBarrier.await();
            System.out.printf("\n%s stop waiting", Thread.currentThread().getName());
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
