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
        //  当类的静态变量或者静态方法被调用时，在调用之前，会执行一次静态代码块中的内容，并且只会执行一次
        System.out.println("执行获取静态变量方法--" + StaticUser.staticValue);
        System.out.println("执行静态方法--" + StaticUser.getStaticValue());
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
