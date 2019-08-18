package com.hsiaoweiyun.currency.condition;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {

    private int maxSize;
    private final List<Date> storage;

    private Lock lock = new ReentrantLock(false);
    private Condition condition = lock.newCondition();

    public Storage(int maxSize) {
        this.maxSize = maxSize;
        storage = new ArrayList<>(maxSize);
    }

    public List<Date> getStorage() {
        return storage;
    }

    public void set(Date date) {
        try{
            lock.lock();

            while (storage.size() == maxSize) {
                try {
                    System.out.println("set thread id: " + Thread.currentThread().getId() + " storage.size(): " + storage.size() + " waiting");
                    condition.await();
                    System.out.println("set thread id: " + Thread.currentThread().getId() + " finish waiting");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            storage.add(date);
            System.out.println("set thread id: " + Thread.currentThread().getId() + "\t" + date.getTime());
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public Date get() {

        try{
            lock.lock();

            while (storage.isEmpty()) {
                try {
                    System.out.println("get thread id: " + Thread.currentThread().getId() + " storage.size(): " + storage.size() + " waiting");
                    condition.await();
                    System.out.println("get thread id: " + Thread.currentThread().getId() + " finish waiting");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Date date = storage.remove(0);
            System.out.println("get thread id: " + Thread.currentThread().getId() + "\t" + date.getTime());
            condition.signalAll();
            return date;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        com.hsiaoweiyun.currency.condition.Storage storage = new com.hsiaoweiyun.currency.condition.Storage(10);

        CountDownLatch countDownLatch = new CountDownLatch(6);

        DateComsumer dc1 = new DateComsumer(storage, countDownLatch);
        DateComsumer dc2 = new DateComsumer(storage, countDownLatch);
        DateComsumer dc3 = new DateComsumer(storage, countDownLatch);

        DateProvider dp1 = new DateProvider(storage, countDownLatch);
        DateProvider dp2 = new DateProvider(storage, countDownLatch);
        DateProvider dp3 = new DateProvider(storage, countDownLatch);


        new Thread(dc1).start();
        new Thread(dc2).start();
        new Thread(dc3).start();


        new Thread(dp1).start();
        new Thread(dp2).start();
        new Thread(dp3).start();


        countDownLatch.await();


        System.out.println("Finish storage size: " + storage.getStorage().size());
    }

}
