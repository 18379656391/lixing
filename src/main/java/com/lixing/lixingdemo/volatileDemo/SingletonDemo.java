package com.lixing.lixingdemo.volatileDemo;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-12-21 17:11
 */
public class SingletonDemo {
    // 提供静态变量保存实例
    private volatile static SingletonDemo instance;

    private SingletonDemo() {

    }

    public static SingletonDemo getInstance() {
        // 第一重检查：针对多个线程同时想要创建对象的情况
        if (instance == null) {
            // 同步代码快锁定
            synchronized (SingletonDemo.class) {
                // 第二重检查：针对比如AB两个线程都为null,第一个线程创建完对象，第二个等待锁的线程拿到锁的情况
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
