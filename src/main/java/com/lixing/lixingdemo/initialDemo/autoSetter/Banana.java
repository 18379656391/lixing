package com.lixing.lixingdemo.initialDemo.autoSetter;

import org.springframework.stereotype.Service;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-06-30 19:17:20
 */
@Service
public class Banana implements Fruit{
    @Override
    public void sell() {
        System.out.println("banana sell...");
    }

    @Override
    public String toString() {
        return "Banana Bean";
    }
}
