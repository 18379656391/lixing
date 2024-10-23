package com.lixing.lixingdemo.juc.volatileDemo;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/4/25
 */
public class Counter {
    private volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        System.out.println(counter.count);
        Thread t1 = new Thread(() -> {
            while (counter.count == 0) {
                // System.out.println("loop--");
            }
            System.out.println("循环结束");
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter.count = 1;
        });

        t1.start();
        t2.start();
        Thread.sleep(2050);
        System.out.println(counter.count);
    }
}
