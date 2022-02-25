package com.lixing.lixingdemo.applicationListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-18 11:11:27
 */
@Component
public class EventPublisher implements ApplicationContextAware {
    private static ApplicationContext context;

    public static void publicEvent(EmailEvent emailEvent) {
        context.publishEvent(emailEvent);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
