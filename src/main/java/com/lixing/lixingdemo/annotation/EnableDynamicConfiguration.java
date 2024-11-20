package com.lixing.lixingdemo.annotation;

import com.lixing.lixingdemo.construct.importSelector.MyImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelector.class)
public @interface EnableDynamicConfiguration {
    boolean enable() default true;
}
