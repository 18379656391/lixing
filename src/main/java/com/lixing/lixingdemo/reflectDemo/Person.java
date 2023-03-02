package com.lixing.lixingdemo.reflectDemo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-04-19 17:08:40
 */
public abstract class Person<T, I> {
    private Class<I> clazz;

    {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        clazz = (Class<I>) params[0];
    }

    public void test(){
        System.out.println(clazz);
    }
}
