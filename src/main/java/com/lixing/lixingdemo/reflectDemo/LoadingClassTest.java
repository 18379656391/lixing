package com.lixing.lixingdemo.reflectDemo;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2025/2/23
 * 参考：https://baijiahao.baidu.com/s?id=1774647635388704060&wfr=spider&for=pc
 */
public class LoadingClassTest {

    @SneakyThrows
    public static void main(String[] args) {
        // 双亲委派原则的实现在loadClass方法中
        LoadingClassTest.class.getClassLoader().loadClass("com.mysql.jdbc.driver");
        // 加载过程，当前类调用Class.forName方法，并且当前类事由AppClassLoader加载的，所以
        // 1.先有AppClassLoader加载Driver类，没有加载再委托给ExtClassLoader加载，没有加载再委托给BootstrapClassLoader加载
        // 2.由于Driver类在CLASSPATH中，不在rt.jar或者ext/*.jar中，所以最终还是由AppClassLoader加载
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        // 采用SPI的方式，破坏双亲委派的加载规则
        // java.sql.DriverManager这个类实rt.jar包下，所有他是由BootStrapClassLoader加载，而com.mysql.cj.jdbc.Driver这个类不在rt.jar中，
        // BootStrapClassLoader又是最顶层的加载器了，那就只能报ClassNotFountException了
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/seata?useSSL=false&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true", "root", "root");

    }
}
