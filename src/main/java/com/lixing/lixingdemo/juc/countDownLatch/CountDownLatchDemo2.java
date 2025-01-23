package com.lixing.lixingdemo.juc.countDownLatch;

import org.elasticsearch.common.util.concurrent.CountDown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-18 13:45
 * 场景2：实现多个线程开始执行任务的最大并行性，模拟100跑步，5名运动员都准备好后，裁判发令，同时开跑
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws Exception{
        CountDownLatch cdl = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            pool.submit(() -> {
                System.out.println("No." + no + ",准备完毕，等待发令");
                try {
                    cdl.await();
                    System.out.println("No." + no + ",开始跑步");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 裁判发令
        Thread.sleep(5000);
        System.out.println("裁判发令，比赛开始。。。。");
        cdl.countDown();
    }
}
