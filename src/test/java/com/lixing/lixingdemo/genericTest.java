package com.lixing.lixingdemo;

import com.lixing.lixingdemo.generic.Apple;
import com.lixing.lixingdemo.generic.Food;
import com.lixing.lixingdemo.generic.Fruits;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-11-29 16:52
 */
@SpringBootTest
public class genericTest {

    @Resource
    private Food food;

    @Resource
    private Fruits fruits;

    @Resource
    private Apple apple;

}
