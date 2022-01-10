package com.lixing.lixingdemo.asyncDemo;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    @SneakyThrows
    void testTask() {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i <5 ; i++) {
            taskDemo.taskRun1(); // 2005
            taskDemo.taskRun2(); // 3009
        }
        Thread.sleep(1000);
        long t2 = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName()+"--main cost" + (t2 - t1)); //1018
        Thread.sleep(10000);
    }
}