package com.lixing.lixingdemo.asyncDemo;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-12-20 10:53
 */
@Component
public class TaskDemo {

    @Async("asyncPoolTaskExecutor")
    @SneakyThrows
    public void taskRun1() {
        long t1 = System.currentTimeMillis();
        Thread.sleep(2000);
        long t2 = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+"--task1 cost:"+ (t2 - t1));
    }

    @Async("asyncPoolTaskExecutor")
    @SneakyThrows
    public void taskRun2() {
        long t1 = System.currentTimeMillis();
        Thread.sleep(3000);
        long t2 = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+"--task2 cost:"+ (t2 - t1));
    }
}
