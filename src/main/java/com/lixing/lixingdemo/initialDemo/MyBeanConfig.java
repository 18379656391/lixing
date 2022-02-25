package com.lixing.lixingdemo.initialDemo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-07 16:18
 */
@Configuration
public class MyBeanConfig {

    @Bean(initMethod = "init", destroyMethod = "myDestroy")
    public User user(){
        User user = new User();
        user.setAge("22");
        user.setName("lixing");
        return user;
    }
    @Bean(initMethod = "init")
    public Car getCar(){
        Car car = new Car();
        car.setName("superCar");
        car.setPrice("100w");
        return car;
    }

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
