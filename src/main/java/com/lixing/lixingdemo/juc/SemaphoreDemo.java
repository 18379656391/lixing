package com.lixing.lixingdemo.juc;

import java.util.concurrent.Semaphore;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/11/24
 * 信号量锁,用来限制能同时访问共享资源的线程上限
 * Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 工人数 ：8
        int N = 8;
        // 机器数量
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < N; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("工人" + finalI + "占用一个机器在生产...");
                    Thread.sleep(2000);
                    System.out.println("工人" + finalI + "释放出机器");
                    semaphore.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
