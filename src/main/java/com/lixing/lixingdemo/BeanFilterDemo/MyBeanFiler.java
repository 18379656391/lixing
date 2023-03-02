package com.lixing.lixingdemo.BeanFilterDemo;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-08-21 16:31:50
 * 自定义拦截器，在Springboot启动的时候，自定义启动Bean的规则
 */
public class MyBeanFiler implements TypeFilter {

    /**
     *
     * @param metadataReader 获取当前正在扫描的类的信息
     * @param metadataReaderFactory 获取其他类的任何信息
     * @return 返回true的时候表示符合过滤条件，false为不符合
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前扫描的资源信息
        Resource resource = metadataReader.getResource();
        if (classMetadata.getClassName().contains("demo")) {
            return true;
        }
        return false;
    }
}
