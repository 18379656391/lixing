package com.lixing.lixingdemo.reflectDemo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-04-19 17:09:47
 */
public class Student extends Person<Integer, Boolean>{

    public static void main(String[] args) {
        Student student = new Student();
        student.test();
        System.out.println("----------------------------");

        Class clazz = student.getClass();
        // 获得该类的父类
        System.out.println(clazz.getSuperclass());

        // 获得带有泛型的父类，Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型
        Type type = clazz.getGenericSuperclass();
        System.out.println(type);

        // ParameterizedType:参数泛型化，即泛型
        ParameterizedType p = (ParameterizedType) type;
        // 获取泛型参数数组
        Class c1 = (Class) p.getActualTypeArguments()[0];
        System.out.println(c1);
        Class c2 = (Class) p.getActualTypeArguments()[1];
        System.out.println(c2);

    }
}
