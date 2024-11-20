package com.lixing.lixingdemo.construct.expand;

import com.alibaba.fastjson.JSON;
import com.example.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/26
 */
@Component
@Slf4j
public final class ServiceContainer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Set<String> AOP_IGNORE_SET = Collections.synchronizedSet(new HashSet<>());

    @Resource
    private HelloService helloService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("aopIgnore服务:{}", JSON.toJSONString(AOP_IGNORE_SET));
        System.out.println(helloService.sayHello("==自定义starter引入Bean成功=="));
    }

    public static void addAopIgnoreService(String serviceId) {
        AOP_IGNORE_SET.add(serviceId);
    }

    public static boolean isAopIgnoreService(String serviceId) {
        return AOP_IGNORE_SET.contains(serviceId);
    }
}
