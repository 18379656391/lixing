package com.lixing.lixingdemo.juc.ReentrantLock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2025/2/16
 */
public class LockParkDemo {

    public static void main(String[] args) {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1执行");
                LockSupport.park();
                System.out.println("线程1执行解阻塞后继续执行");
            }
        }, "线程1");
        thread1.start();

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2执行");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(thread1);
            }
        }, "线程2");
        thread2.start();
    }
}
