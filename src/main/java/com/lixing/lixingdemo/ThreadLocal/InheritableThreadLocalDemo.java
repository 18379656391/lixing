package com.lixing.lixingdemo.ThreadLocal;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2023-03-02 14:17:29
 */
public class InheritableThreadLocalDemo {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("mainThread--tag1");
        System.out.println(Thread.currentThread().getName() + "主线程获取threadLocal值：" + threadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "子线程获取threadLocal值：" + threadLocal.get());
            }
        }).start();

        Thread.sleep(1000);

        inheritableThreadLocal.set("mainThread--tag2");
        System.out.println(Thread.currentThread().getName() + "主线程获取inheritableThreadLocal值：" + inheritableThreadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "子线程获取inheritableThreadLocal值：" + inheritableThreadLocal.get());
            }
        }).start();
    }
}
