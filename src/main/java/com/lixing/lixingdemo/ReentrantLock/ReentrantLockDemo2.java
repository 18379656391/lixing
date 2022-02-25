package com.lixing.lixingdemo.ReentrantLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-20 17:18
 * 公平锁的实现：谁等的时间最长，谁就先获得锁
 */
public class ReentrantLockDemo2 {
    // true实现公平锁,false默认为空表示非公平锁，cpu时间片轮转到哪个线程哪个线程就先获得锁
    public static final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            pool.submit(() -> {
                test();
            });
        }
    }
    public static void test() {
        for (int i = 0; i < 2; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取了锁");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        }
    }

}
