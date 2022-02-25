package com.lixing.lixingdemo.initialDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-07 16:08
 */
public class User implements ApplicationContextAware, InitializingBean, DisposableBean {

    private String name;

    private String age;

    public User() {
        System.out.println("("+Thread.currentThread().getName()+")1-->创建bean实例");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("("+Thread.currentThread().getName()+")2-->设置bean的name属性");
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        System.out.println("("+Thread.currentThread().getName()+")2-->设置bean的age属性");
    }

    public void init(){
        System.out.println("("+Thread.currentThread().getName()+")6-->调用init-method指定的属性指定的方法");
    }

    public void myDestroy() {
        System.out.println("("+Thread.currentThread().getName()+")9-->调用destroy-method指定的属性指定的方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("("+Thread.currentThread().getName()+")8-->调用DisposableBean接口的destroy接口");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("("+Thread.currentThread().getName()+")5-->调用InitializingBean接口的afterPropertiesSet方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("("+Thread.currentThread().getName()+")3-->调用对应Aware接口的方法");
    }
}
