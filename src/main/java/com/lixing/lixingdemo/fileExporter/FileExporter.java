package com.lixing.lixingdemo.fileExporter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-14 13:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileExporter {

    String busiType();

    String busiTypeSub();

    String serviceId() default "";
}
