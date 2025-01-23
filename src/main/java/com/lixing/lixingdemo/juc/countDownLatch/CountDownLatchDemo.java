package com.lixing.lixingdemo.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-17 18:24
 * 场景1：启动一个服务时，等待多个组件加载完毕后，之后继续执行
 * CountDownLatch的缺点是不能重用，如果需要重新计数，可以使用CyclicBarrier
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        // countDownLatch使一个线程等待其他线程各自执行完毕后再执行，通过计数器来实现，每一个线程执行完毕后，计数器-1，直到为0，等待线程才开始执行
        final CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程执行----");
        // 第一个子线程执行
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程:" + Thread.currentThread().getName() + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es1.shutdown();

        // 第二个子线程执行
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程:" + Thread.currentThread().getName() + "执行");
                latch.countDown();
            }
        });
        es2.shutdown();
        System.out.println("等待两个线程执行完毕");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
        System.out.println(Thread.currentThread().getName() + "run------");
    }
}
