package com.lixing.lixingdemo.juc.volatileDemo;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-12-21 17:11
 * https://blog.csdn.net/lzhNox/article/details/125979527
 * 当一个变量被volatile修饰之后：
 * 1.保证了这个变量在不同的线程之间的可见性，一个线程修改了它，另一个线程马上知道（会将线程计算的结果值立马写入主存中，并且使其他线程对应变量的缓存无效并重新从主存中读取）
 * 2.禁止指令重排序
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
