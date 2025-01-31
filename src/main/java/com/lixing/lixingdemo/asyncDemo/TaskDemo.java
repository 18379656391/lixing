package com.lixing.lixingdemo.asyncDemo;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

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

    public void test3() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setKeepAliveSeconds(200);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("lixing-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        executor.submit(new Runnable() {
            @Override
            public void run() {
                taskRun2();
            }
        });
    }
}
