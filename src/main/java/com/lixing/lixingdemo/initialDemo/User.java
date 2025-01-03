package com.lixing.lixingdemo.initialDemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-07 16:08
 */
public class User implements ApplicationContextAware, BeanNameAware, BeanClassLoaderAware,BeanFactoryAware, InitializingBean, DisposableBean, Serializable {

    static{
        System.out.println("("+Thread.currentThread().getName()+")001-->静态代码块执行了");
    }
    {
        System.out.println(this.getClass().getName() + "("+Thread.currentThread().getName()+")002-->普通代码块执行了");
    }

    private String name;

    private String age;

    public User() {
        System.out.println("("+Thread.currentThread().getName()+")1-->构造函数创建bean实例");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("("+Thread.currentThread().getName()+")2-->set方法进行依赖注入，设置bean的name属性");
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        System.out.println("("+Thread.currentThread().getName()+")2-->set方法进行依赖注入，设置bean的age属性");
    }

    @PostConstruct
    public void postConstructMethod(){
        System.out.println("("+Thread.currentThread().getName()+")-->调用postConstruct初始化方法");
    }

    public void init(){
        System.out.println("("+Thread.currentThread().getName()+")6-->调用init-method指定的属性指定的方法");
    }

    public void myDestroy() {
        System.out.println("("+Thread.currentThread().getName()+")9-->调用destroy-method指定的属性指定的方法");
    }

    @PreDestroy
    public void preDestroyMethod(){
        System.out.println("("+Thread.currentThread().getName()+")-->调用preDestroy方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("("+Thread.currentThread().getName()+")8-->调用DisposableBean接口的destroy接口");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 给用户最后一次机会来进行赋值或者调用操作
        System.out.println("("+Thread.currentThread().getName()+")5-->调用InitializingBean接口的afterPropertiesSet方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("("+Thread.currentThread().getName()+")3-->applicationContextAware重写方法执行，调用对应Aware接口的方法");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("("+Thread.currentThread().getName()+")3-->BeanCLassLoaderAware重写方法执行，调用对应Aware接口的方法");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("("+Thread.currentThread().getName()+")3-->BeanFactoryAware重写方法执行，调用对应Aware接口的方法");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("("+Thread.currentThread().getName()+")3-->BeanNameAware重写方法执行，调用对应Aware接口的方法");
    }
}
