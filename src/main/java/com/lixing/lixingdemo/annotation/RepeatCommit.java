package com.lixing.lixingdemo.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatCommit {

    /**
     * 限流前缀，用来区分不同的场景
     * @return
     */
    String prefix() default "";

    /**
     * 限流key,用来辨别是否是一次重复的请求，支持spel表达式，可以执行入参中的特定属性
     * @return
     */
    String key() default "";

    /**
     * 限制在多少时间禁止重复请求
     * @return
     */
    int time() default 3;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    String message() default "操作太频繁，请稍后再试";

}
