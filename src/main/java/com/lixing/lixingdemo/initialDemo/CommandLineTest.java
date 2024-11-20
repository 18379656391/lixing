package com.lixing.lixingdemo.initialDemo;

import com.alibaba.fastjson.JSON;
import com.lixing.lixingdemo.dynamicProxy.cglib.CglibProxy;
import com.lixing.lixingdemo.dynamicProxy.cglib.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/16
 * CommandLineRunner是Spring Boot框架中提供的一种机制，允许开发人员在Spring Boot应用程序完成启动后执行一些自定义的初始化代码
 * 当一个类实现了CommandLineRunner接口并被Spring容器管理（通常通过@Component注解），SpringBoot会在应用启动完成后自动调用run方法
 * CommandLineRunner实例的执行顺序是不确定的，除非使用@Order注解来指定优先级。
 * 与CommandLineRunner类似的还有ApplicationRunner接口，它们的主要区别在于ApplicationRunner的run方法接收一个ApplicationArguments对象作为参数，这提供了更多关于命令行参数的细节，如是否包含选项、非选项参数等。
 */
@Component
@Order(1)
public class CommandLineTest implements CommandLineRunner {

    @Autowired
    private Environment environment;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner args--" + JSON.toJSONString(args));
        System.out.println("("+Thread.currentThread().getName()+")9999-->CommandLineRunner代码执行---");
        //System.out.println("Application started with arguments:" + String.join(",", args));
        //System.out.println("Running in environment:" + environment.getActiveProfiles()[0]);
        // cglib启动测试
        TargetService targetService = new TargetService();
        CglibProxy cglibProxy = new CglibProxy(targetService);
        TargetService proxyInstance = (TargetService) cglibProxy.getProxyInstance();
        proxyInstance.delete();
    }
}
