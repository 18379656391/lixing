package com.lixing.lixingdemo.initialDemo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-08 15:07
 */
@Component
public class Car {
    private String name;
    private String price;

    public Car() {
    }

    public Car(String name, String price) {
        this.name = name;
        this.price = price;
    }

    // 构造函数之后执行，init（）方法之前执行
    @PostConstruct
    private void start(){
        this.price = "0";
        System.out.println("Car的属性初始化,执行start方法。。。price:" + this.price);
    }

    private void init(){
        this.name = "car";
        System.out.println("Car的属性初始化,执行init方法。。。name:" + this.price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
