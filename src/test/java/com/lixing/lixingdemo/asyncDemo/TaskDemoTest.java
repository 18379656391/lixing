package com.lixing.lixingdemo.asyncDemo;

import com.lixing.lixingdemo.asyncDemo.Future.CompletableFutureTest;
import com.lixing.lixingdemo.asyncDemo.Future.FutureTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.annotation.security.RunAs;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-12-20 11:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class TaskDemoTest {

    @Autowired
    private TaskDemo taskDemo;

    @Resource
    private FutureTest futureTest;

    @Resource
    private CompletableFutureTest completableFutureTest;
    @Test
    @SneakyThrows
    void testTask() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i <5 ; i++) {
            //taskDemo.taskRun1(); // 2005
            taskDemo.taskRun2(); // 3009
        }
        Thread.sleep(1000);
        long t2 = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+"--main cost" + (t2 - t1)); //1018
        Thread.sleep(10000);
    }

    @Test
    @SneakyThrows
    void testTask1() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i <5 ; i++) {
            taskDemo.test3();
        }
        Thread.sleep(1000);
        long t2 = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+"--main cost" + (t2 - t1)); //1018
        Thread.sleep(10000);
    }

    @Test
    @SneakyThrows
    void FutureExecuteTest(){
        futureTest.FutureExecute();
    }

    @Test
    @SneakyThrows
    void CompleteFutureExecuteTest(){
        completableFutureTest.CompletableFutureExecute();
    }

    @Test
    @SneakyThrows
    void FutureThenRunTest(){
        completableFutureTest.FutureThenRunTest();
    }

    @Test
    @SneakyThrows
    void FutureThenAcceptTest(){
        completableFutureTest.FutureThenAcceptTest();
    }

    @Test
    @SneakyThrows
    void FutureThenApplyTest(){
        completableFutureTest.FutureThenApplyTest();
    }

    @Test
    @SneakyThrows
    void FutureExceptionTest(){
        completableFutureTest.FutureExceptionTest();
    }

    @Test
    @SneakyThrows
    void FutureWhenTest(){
        completableFutureTest.FutureWhenTest();
    }
}