package com.lixing.lixingdemo.initialDemo;

import com.example.service.HelloService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-12-16 14:29
 */
@Component
public class ConfigLoader implements InitializingBean, ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("("+Thread.currentThread().getName()+ ")["+this.getClass().getName()+"]开始执行afterPropertiesSet·····");
        ConfigLoader.initialMethod();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("("+Thread.currentThread().getName()+ ")["+this.getClass().getName()+"]开始执行setApplicationContext·····");
        context = applicationContext;
    }

    public static String initialMethod() {
        System.out.println("("+Thread.currentThread().getName()+ ")["+ConfigLoader.class.getName()+"]开始加载初始化配置····························");
        return "配置加载完成································";
    }

    @PostConstruct
    private void test(){
        System.out.println("("+Thread.currentThread().getName()+ ")["+this.getClass().getName()+"]configLoader调用postConstruct方法。。。test:");
    }
}
