package com.lixing.lixingdemo.applicationListener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextListener;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/18
 * ServeletContext和事件监听生效的时机，实际也是在Application的ContextRefresh期间
 */
@Component
public class MyServletContextListener implements ServletContextListener {

    /**
     * ServletContext初始化后执行
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(javax.servlet.ServletContextEvent servletContextEvent) {
        System.out.println("-------------------------ServletContext Destroyed-------------------------");
    }

    /**
     * ServletContext销毁触发，一般就是应用结束的时候
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent servletContextEvent) {
        System.out.println("-------------------------ServletContext Initialized-------------------------");
    }
}
