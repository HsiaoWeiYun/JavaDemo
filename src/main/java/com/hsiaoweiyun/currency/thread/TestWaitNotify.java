package com.hsiaoweiyun.currency.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class TestWaitNotify {

    private static final Object lock = new Object();

    @SneakyThrows
    public static void main(String[] args) {
        SumCal sumCal = new SumCal();
        Thread t1 = new Thread(sumCal);
        t1.start();
        synchronized (lock){
            lock.wait();
        }
        System.out.println(sumCal.sum);
    }

    public static class SumCal implements Runnable{

        int sum = 0;

        @SneakyThrows
        @Override
        public void run() {
            TimeUnit.MILLISECONDS.sleep(100L);
            synchronized (lock){
                for (int i = 0; i < 50; i++) {
                    sum++;
                }
                lock.notifyAll();
            }
        }
    }

}
