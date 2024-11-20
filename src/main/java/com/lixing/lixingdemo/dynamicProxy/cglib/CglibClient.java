package com.lixing.lixingdemo.dynamicProxy.cglib;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/11/6
 * 查看cglib动态代理生成的类
 * System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"/Users/lixing/Documents/Project/lixing/lixing/src/main/java/com/lixing/lixingdemo/dynamicProxy/cglib");
 */
public class CglibClient {

    public static void main(String[] args) {
// cglib是通过继承的方式生成代理类，可以代理没有实现接口的类，可以对对象中的方法进行代理，无法代理final的类和方法或者private的方法（因为继承）
// -实现原理：cglib是一个代码生成的库，它可以在运行时动态生成目标类的子类，并通过子类来拦截和增强方法的调用
// -实现方式：cglib通过Enhancer类来创建代理对象，Enhancer会为目标类生成一个子类，并在子类中重写目标方法。重写方法中，会插入横切逻辑（前置通知、后置通知）
        TargetService targetService = new TargetService();
        CglibProxy cglibProxy = new CglibProxy(targetService);
        TargetService proxyInstance = (TargetService) cglibProxy.getProxyInstance();
        proxyInstance.delete();
    }
}
