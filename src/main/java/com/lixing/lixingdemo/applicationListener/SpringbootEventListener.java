package com.lixing.lixingdemo.applicationListener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.context.event.*;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/16
 * 测试验证springboot中各个事件的调用先后顺序
 */
@Component
@Slf4j
public class SpringbootEventListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //log.info("event=================" + event.getClass().getName() + "监听事件");
        if (event instanceof ApplicationStartingEvent) {
            // 这个事件在 Spring Boot 应用运行开始时，且进行任何处理之前发送（除了监听器和初始化器注册之外）。
            // 事件无效，因为这个事件社死在ApplicationContext创建前就发生了，所以不可以通过@Bean的方式监听
            log.info("event=================" + "1.收到ApplicationStartingEvent监听事件");
        }
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            // 这个事件在当已知要在上下文中使用 Spring 环境（Environment）时，在 Spring 上下文（context）创建之前发送。
            // 事件无效
            log.info("event=================" + "2.收到ApplicationEnvironmentPreparedEvent监听事件");
        }
        if (event instanceof ApplicationContextInitializedEvent) {
            // 这个事件在当 Spring 应用上下文（ApplicationContext）准备好了，并且应用初始化器（ApplicationContextInitializers）已经被调用，在 bean 的定义（bean definitions）被加载之前发送。
            // 事件无效
            log.info("event=================" + "3.收到ApplicationContextInitializedEvent监听事件");
        }
        if (event instanceof ApplicationPreparedEvent) {
            //这个事件是在 Spring 上下文（context）刷新之前，且在 bean 的定义（bean definitions）被加载之后发送。
            // 事件无效
            log.info("event=================" + "4.收到ApplicationPreparedEvent监听事件");
        }
        if (event instanceof WebServerInitializedEvent) {
            //这个 Web 服务器初始化事件在 WebServer 启动之后发送，对应的还有 ServletWebServerInitializedEvent（Servlet Web 服务器初始化事件）、ReactiveWebServerInitializedEvent（响应式 Web 服务器初始化事件）。
            log.info("event=================" + "4.1.收到WebServerInitializedEvent监听事件");
        }
        if (event instanceof ContextRefreshedEvent) {
            //这个上下文刷新事件是在 Spring 应用上下文（ApplicationContext）刷新之后发送。
            log.info("event=================" + "4.2.收到ContextRefreshedEvent监听事件");
        }
        if (event instanceof ApplicationStartedEvent) {
            //这个事件是在 Spring 上下文（context）刷新之后，且在 application/ command-line runners 被调用之前发送。
            log.info("event=================" + "5.收到ApplicationStartedEvent监听事件");
        }
        if (event instanceof AvailabilityChangeEvent) {
            //这个事件紧随上个事件之后发送，状态：ReadinessState.CORRECT，表示应用已处于活动状态。
            log.info("event=================" + "6.收到AvailabilityChangeEvent监听事件");
        }
        if (event instanceof ApplicationReadyEvent) {
            //这个事件在任何 application/ command-line runners 调用之后发送。
            log.info("event=================" + "7.收到ApplicationReadyEvent监听事件");
        }
        if (event instanceof AvailabilityChangeEvent) {
            // 这个事件紧随上个事件之后发送，状态：ReadinessState.ACCEPTING_TRAFFIC，表示应用可以开始准备接收请求了。
            log.info("event=================" + "8.收到AvailabilityChangeEvent监听事件");
        }
        if (event instanceof ApplicationFailedEvent) {
            //这个事件在应用启动异常时进行发送。
            log.info("event=================" + "9.收到ApplicationFailedEvent监听事件");
        }
    }
}
