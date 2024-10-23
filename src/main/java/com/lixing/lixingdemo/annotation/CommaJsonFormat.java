package com.lixing.lixingdemo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lixing.lixingdemo.construct.formatter.CustomJsonFieldDeserializer;
import com.lixing.lixingdemo.construct.formatter.CustomJsonFieldSerializer;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target( ElementType.FIELD)
// 让jackson的注解拦截器（JacksonAnnotationIntrospector）能发现当前注解
//@JacksonAnnotationsInside
//@JsonSerialize(using = CustomJsonFieldSerializer.class)
//@JsonDeserialize(using = CustomJsonFieldDeserializer.class)
// https://yunyanchengyu.blog.csdn.net/article/details/137656776
public @interface CommaJsonFormat {
    String value() default ",";
}
