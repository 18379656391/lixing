package com.lixing.lixingdemo.ReentrantLock;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-21 16:44
 * 限时等待：通过tryLock方法，可以选择传入时间参数，表示指定等待时间，不传则表示立即返回锁申请的结果：true表示成功，false表示失败
 */
public class ReentrantLockDemo4 {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadDemo(lock1, lock2),"线程1");
        Thread thread1 = new Thread(new ThreadDemo(lock1, lock2),"线程2");
        thread.start();
        thread1.start();
    }

    static class ThreadDemo implements Runnable {
        Lock firstLock;
        Lock secondLock;

        public ThreadDemo(Lock firstLock, Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"trylock1---");
                while(!lock1.tryLock(500,TimeUnit.MILLISECONDS)){
                    System.out.println(Thread.currentThread().getName()+"trylock1 is false");
                }
                System.out.println(Thread.currentThread().getName()+"trylock1 is true");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"trylock2---");
                while(!lock2.tryLock(500,TimeUnit.MILLISECONDS)){
                    System.out.println(Thread.currentThread().getName()+"trylock2 is false");
                }
                System.out.println(Thread.currentThread().getName()+"trylock2 is true");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                firstLock.unlock();
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName() + "获取到了资源，正常结束");
            }
        }
    }
}
