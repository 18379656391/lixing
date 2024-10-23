package com.lixing.lixingdemo.construct.formatter;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lixing.lixingdemo.annotation.CommaJsonFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/10
 */
@Configuration
public class CustomJsonConfig {

    @Bean
    // @Scope("prototype")
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
        ObjectMapper build = builder.createXmlMapper(false).build();
        AnnotationIntrospector annotationIntrospector = build.getSerializationConfig().getAnnotationIntrospector();
        //AnnotationIntrospector annotationIntrospector = build.getDeserializationConfig().getAnnotationIntrospector();
        // 将自定义注解内省器加入到Jackson注解内省器集合里，AnnotationIntrospector是双向链表结构
        AnnotationIntrospector pair = AnnotationIntrospectorPair.pair(annotationIntrospector, new CommaJsonAnnotationIntrospector());
        build.setAnnotationIntrospector(pair);
        return build;
    }
}
