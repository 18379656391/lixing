package com.lixing.lixingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.lixing.lixingdemo"})
//开启Aop,默认为false(JDK代理模式) true(Cglib模式)
// (this) JDK代理时，指向接口和代理类proxy（通过反射），cglib（通过修改底层字节码）代理时 指向接口和子类(不使用proxy)
// JDK动态代理是通过实现接口的方式，只能代理接口中的方法，cglib是通过继承的方式，可以对对象中的方法进行代理，无法代理final的类和方法或者private的方法
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
public class LixingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LixingDemoApplication.class, args);
    }

}
