package com.hsiaoweiyun.currency.condition;

import com.hsiaoweiyun.currency.condition.Storage;

import java.util.concurrent.CountDownLatch;

public class DateComsumer implements Runnable {

    private Storage storage;
    private CountDownLatch countDownLatch;

    public DateComsumer(Storage storage, CountDownLatch countDownLatch) {
        this.storage = storage;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                storage.get();
            }
        } finally {
            countDownLatch.countDown();
        }
    }
}
