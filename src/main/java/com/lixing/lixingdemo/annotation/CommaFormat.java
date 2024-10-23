package com.lixing.lixingdemo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/9/23
 * 自定义注解，将含有分隔符的字符串，转换成数组
 * 注意：前台使用contentType: "application/json"，后台使用@RequestBody来接收数据的话，此注解不生效，因为指定是json的话就会默认使用jackson的解析器
 * 只适用于表单提交数据或者url传参
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface CommaFormat {
    String value() default ",";
}
