package com.lixing.lixingdemo.construct.importSelector;

import com.lixing.lixingdemo.annotation.EnableDynamicConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.Resource;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/28
 * 用于动态加载配置类
 * 原理：当Spring容器处理一个带有@EnableAutoConfiguration（或者其他带有@Import注解的配置类）的配置类时，它会查找该类上的@Import注解，
 * 并实例化指定的类。如果这个类实现了ImportSelector接口，Spring就会调用它的selectImports方法来获取要导入的类的列表。
 *
 */
@Slf4j
public class MyImportSelector implements ImportSelector, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes enableAspectJAutoProxy = attributesFor(importingClassMetadata, EnableDynamicConfiguration.class);
        if (enableAspectJAutoProxy != null) {
            log.info("获取到EnableDynamicConfiguration注解的属性值--enable:{}", enableAspectJAutoProxy.getBoolean("enable"));
        }
        // 读取配置文件中配置的需要加载的配置类，进行动态加载注入到容器中
        String property = environment.getProperty("lixing.spring.import.config.path");
        if (StringUtils.isNotBlank(property)) {
            log.info("加载动态配置类---:{}", property);
            return property.split(",");
        }
        log.info("没有需要动态加载的配置类---");
        return new String[0];
    }

    private AnnotationAttributes attributesFor(AnnotationMetadata metadata, Class<?> annotationClass) {
        return AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(annotationClass.getName(), false));
    }
}
