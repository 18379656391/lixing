package com.lixing.lixingdemo.juc.CyclicBarrier;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/11/24
 * CyclicBarrier循环栅栏，用来进行线程协作，等待线程满足某个计数。构造时设置【计数个数】，每个线程执行到某个需要“同步”的时刻调用 await() 方法进行等待，当等待的线程数满足『计数个数』时，继续执行
 * 可以形象的比喻为：人满发车
 * 1.和CountDownLatch类似，但是CyclicBarrier可以重复使用，而CountDownLatch只能使用一次
 * 2.CountDownLatch的计数和阻塞方法是分开的两个方法，而CyclicBarrier是一个方法。
 * 3.CyclicBarrier的构造器还有一个Runnable类型的参数，在计数为0时会执行其中的run方法。
 */
@Slf4j
public class CyclicBarrierDemo {


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        threadPool.submit(() -> {
            try {
                log.info("task1 start....waiting count:{}", cyclicBarrier.getNumberWaiting());
                int await = cyclicBarrier.await();
                log.info("task1 await..count:{}", await);
                log.info("task1 wait....waiting count:{}", cyclicBarrier.getNumberWaiting());
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            log.info("task1 finish....");
        });

        threadPool.submit(() -> {
            try {
                Thread.sleep(2000);
                log.info("task2 start....waiting count:{}", cyclicBarrier.getNumberWaiting());
                int await = cyclicBarrier.await();
                log.info("task2 await..count:{}", await);
                log.info("task2 wait....waiting count:{}", cyclicBarrier.getNumberWaiting());
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            log.info("task2 finish....");
        });
        threadPool.shutdown();
    }
}
