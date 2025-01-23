package com.lixing.lixingdemo.juc.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-18 14:18
 * 模拟100跑步，5名运动员都准备好后，裁判发令，同时开跑,
 * 所有人都到达终点后，比赛结束
 */
public class CountDownLatchDemo3 {
    public static void main(String[] args) throws Exception{
        CountDownLatch cdl1 = new CountDownLatch(1);
        CountDownLatch cdl2 = new CountDownLatch(5);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            pool.submit(() -> {
                System.out.println("No." + no + "选手准备完毕，等待发令。。");
                try {
                    cdl1.await();
                    System.out.println("No." + no + "开始起跑");
                    Thread.sleep(new Random().nextInt(15000));
                    System.out.println("No." + no + "到达终点");
                    cdl2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(5000);
        cdl1.countDown();
        System.out.println("裁判发令,开始比赛。。。。");

        cdl2.await();
        System.out.println("所有选手到达终点，比赛结束。。。");

    }
}
