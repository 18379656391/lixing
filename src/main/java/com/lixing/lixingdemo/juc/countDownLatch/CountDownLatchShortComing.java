package com.lixing.lixingdemo.juc.countDownLatch;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/11/24
 * 研究 CountDownLatch 的缺点
 * 1. CountDownLatch 的计数器不能重复使用，想要重复使用CountdownLatch进行同步，必须创建多个CountDownLatch对象。
 */
@Slf4j
public class CountDownLatchShortComing {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 如果复用，逻辑就有问题了
        // CountDownLatch cdl = new CountDownLatch(2);
        for (int i = 0; i < 3; i++) {
            CountDownLatch cdl = new CountDownLatch(2);
            threadPool.submit(() -> {
                log.info("task1 start....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                cdl.countDown();
            });

            threadPool.submit(() -> {
                log.info("task2 start....");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                cdl.countDown();
            });

            cdl.await();
            log.info("Task1 and Task2 finish...");
        }
        threadPool.shutdown();
    }
}
