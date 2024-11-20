package com.lixing.lixingdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * @Target(ElementType.FIELD) 该定义作用在类的属性上
 * @Target(ElementType.METHOD) 该定义作用在类的方法上
 * 其他的声明范围有：
 * ElementType.TYPE：接口、类、枚举、注解
 * ElementType.PARAMETER：方法参数
 * ElementType.CONSTRUCTOR：构造函数
 * ElementType.LOCAL_VARIABLE：局部变量
 * ElementType.ANNOTATION_TYPE：注解
 * ElementType.PACKAGE：包
 */

/**
 * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 * 这3个生命周期分别对应于：Java源文件(.java文件) ---> .class文件 ---> 内存中的字节码。
 */

/**
 * @Documented
 *用于控制javadoc是否显示注解。当添加@Documented时，注解将出现在生成的文档中
 */
public @interface AopIgnore {

}
