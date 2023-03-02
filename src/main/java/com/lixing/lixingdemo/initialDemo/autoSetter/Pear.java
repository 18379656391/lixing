package com.lixing.lixingdemo.initialDemo.autoSetter;

import org.springframework.stereotype.Service;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-06-30 19:16:53
 */
@Service
public class Pear implements Fruit{
    @Override
    public void sell() {
        System.out.println("pear sell...");
    }
}
