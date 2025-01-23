package com.lixing.lixingdemo.initialDemo;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/11/20
 */
public class StaticUser {

    static{
        System.out.println(StaticUser.class.getName() +"("+Thread.currentThread().getName()+")-->静态代码块执行了");
    }
    {
        System.out.println(this.getClass().getName() + "("+Thread.currentThread().getName()+")-->普通代码块执行了");
    }

    // 当类的静态变量或者静态方法被调用时，会执行一次静态代码块中的内容
    public static Integer staticValue = 1;

    public static Integer getStaticValue(){
        return staticValue;
    }
}
