package com.lixing.lixingdemo.applicationListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/18
 */
public class SpringApplicationEventListener implements ApplicationListener<SpringApplicationEvent>{

    // @EventListener 注解，在ContextRefreshedEvent事件前的那些事件，都是监听不到的，原理：https://www.bilibili.com/read/cv28239465/
    public void onApplicationEvent(SpringApplicationEvent event) {
        System.out.println("SpringApplicationEvent=================" + event.getClass().getName() + "监听事件");
        if (event instanceof ApplicationStartingEvent) {
            // 这个事件在 Spring Boot 应用运行开始时，且进行任何处理之前发送（除了监听器和初始化器注册之外）。
            //事件无效，因为这个事件社死在ApplicationContext创建前就发生了，所以不可以通过@Bean的方式监听，但是可以在Spring.factories文件中，配置上当前监听器，就可以生效
            System.out.println("SpringApplicationEvent=================" + "1.收到ApplicationStartingEvent监听事件");
        }
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            // 这个事件在当已知要在上下文中使用 Spring 环境（Environment）时，在 Spring 上下文（context）创建之前发送。
            System.out.println("SpringApplicationEvent=================" + "2.收到ApplicationEnvironmentPreparedEvent监听事件");
        }
        if (event instanceof ApplicationContextInitializedEvent) {
            // 这个事件在当 Spring 应用上下文（ApplicationContext）准备好了，并且应用初始化器（ApplicationContextInitializers）已经被调用，在 bean 的定义（bean definitions）被加载之前发送。
            System.out.println("SpringApplicationEvent=================" + "3.收到ApplicationContextInitializedEvent监听事件");
        }
        if (event instanceof ApplicationPreparedEvent) {
            //这个事件是在 Spring 上下文（context）刷新之前，且在 bean 的定义（bean definitions）被加载之后发送。
            System.out.println("SpringApplicationEvent=================" + "4.收到ApplicationPreparedEvent监听事件");
        }
    }
}
