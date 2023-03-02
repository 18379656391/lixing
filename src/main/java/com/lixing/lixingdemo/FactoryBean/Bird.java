package com.lixing.lixingdemo.FactoryBean;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-04-26 15:55:25
 */
public class Bird implements FactoryBean<Bird> {
    @Override
    public Bird getObject() throws Exception {
        // Bird bird = new Bird();
        Object bird = Proxy.newProxyInstance(this.getClass().getClassLoader(), Bird.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                return null;
            }
        });
        return (Bird) bird;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
