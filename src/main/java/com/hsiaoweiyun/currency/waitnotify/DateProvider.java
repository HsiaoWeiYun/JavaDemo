package com.hsiaoweiyun.currency.waitnotify;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class DateProvider implements Runnable {

    private Storage storage;
    private CountDownLatch countDownLatch;

    public DateProvider(Storage storage, CountDownLatch countDownLatch) {
        this.storage = storage;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                storage.set(new Date());
            }
        } finally {
            countDownLatch.countDown();
        }
    }
}
