package com.lixing.lixingdemo.construct.expand;

import com.lixing.lixingdemo.annotation.AopIgnore;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/26
 */
@Component
@Slf4j
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private Environment environment;
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        BeanDefinition bd = new RootBeanDefinition();
//        bd.setBeanClassName("");
//        beanDefinitionRegistry.registerBeanDefinition("", bd));
    }

    @Override
    @SneakyThrows
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("("+Thread.currentThread().getName()+")-->BeanDefinitionRegistryPostProcessor重写方法执行---");
        for (String bdName : configurableListableBeanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(bdName);
            if (beanDefinition instanceof AbstractBeanDefinition) {
                AbstractBeanDefinition abstractBeanDefinition = (AbstractBeanDefinition) beanDefinition;
                if (StringUtils.isNotBlank(abstractBeanDefinition.getBeanClassName()) && abstractBeanDefinition.getBeanClassName().startsWith("com.lixing.lixingdemo")) {
                    Class<?> clz = abstractBeanDefinition.resolveBeanClass(MyBeanDefinitionRegistryPostProcessor.class.getClassLoader());
                    if (null != clz){
                        Method[] methods = clz.getMethods();
                        for (Method method : methods) {
                            if (null != method.getDeclaredAnnotation(AopIgnore.class)) {
                                ServiceContainer.addAopIgnoreService(clz.getName() + "." + method.getName());
                            }
                        }
                    }
                }
            }
        }
        // 关闭容器循环引用
        this.closeBeanFactoryCircularReference(configurableListableBeanFactory);
    }

    /**
     * 关闭容器循环引用
     */
    private void closeBeanFactoryCircularReference(ConfigurableListableBeanFactory beanFactory) {
        if (beanFactory instanceof AbstractAutowireCapableBeanFactory) {
            AbstractAutowireCapableBeanFactory abf = (AbstractAutowireCapableBeanFactory) beanFactory;
            abf.setAllowCircularReferences(false);
            log.info("关闭Spring容器循环引用 --- finish!");
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        log.info("当前激活的配置文件信息：{}", (Object[]) environment.getActiveProfiles());
    }
}
