package com.lixing.lixingdemo.initialDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-07 16:16
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("("+Thread.currentThread().getName()+")7-->调用MyBeanPostProcessor的postProcessAfterInitialization方法" + "name:" + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("("+Thread.currentThread().getName()+")4-->调用MyBeanPostProcessor的postProcessBeforeInitialization方法" + "name:" + beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
