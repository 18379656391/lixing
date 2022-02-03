package com.lixing.lixingdemo.Synchronized;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-18 16:03
 * 使用wait()、notify()和notifyAll()时需要先对调用对象加锁，调用wait()方法后会==释放锁==。
 * 调用wait()方法之后，线程状态由RUNNING变为WAITING，并将当前线程放置到对象的等待队列中。
 * notify()或notifyAll()方法调用后，等待线程不会立刻从wait()中返回，需要等该线程释放锁之后，才有机会获取锁之后从wait()返回。
 * notify()方法将等待队列中的一个等待线程从等待队列中移动到同步队列中；notifyAll()方法则是把等待队列中的所有线程都移动到同步队列中；被移动的线程状态从WAITING变为BLOCKED。
 * 从wait()方法返回的前提是，改线程获得了调用对象的锁。
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{
        Thread A = new Thread(new Wait(), "wait thread");
        A.start();
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        Thread B = new Thread(new Notify(), "notify thread");
        B.start();
        long end = System.currentTimeMillis();
        System.out.println("cost:" + String.valueOf(end-start));
    }

    static class Wait implements Runnable{

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "flag is true");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "flag is false");
            }
        }
    }

    static class Notify implements Runnable{

        @Override
        public void run() {
            synchronized (lock) {
                flag = false;
                lock.notifyAll();
                try {
                    TimeUnit.SECONDS.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
