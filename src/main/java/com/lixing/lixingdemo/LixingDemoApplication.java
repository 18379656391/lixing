package com.lixing.lixingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//开启Aop,默认为false(JDK代理模式) true(Cglib模式)
// (this) JDK代理时，指向接口和代理类proxy，cglib代理时 指向接口和子类(不使用proxy)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
public class LixingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LixingDemoApplication.class, args);
    }

}
