package com.lixing.lixingdemo.dynamicProxy.jdkProxy;


import javax.annotation.Resource;

/**
 *  扩展，查看jdk动态代理生成的类
 *  jdk8版本及之前版本：
 *  1、idea--edit Configurations--Modify options--add VM options -- -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
 *  2、System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
 *  jdk8之后版本：
 *  1、idea--edit Configurations--Modify options--add VM options -- -Djdk.proxy.ProxyGenerator.saveGeneratedFiles=true
 *  2、System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
 */
public class Client {

    public static void main(String[] args) {
        // 查看jdk动态代理生成的类
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        //真实角色
        DaoServiceImpl target = new DaoServiceImpl();
        //通过实现InvocationHandler接口创建自己的调用处理器，然后使用Proxy类的newProxyInstance方法生成代理对象。
        // 这个代理对象会实现目标对象所实现的接口，并在调用目标方法时，将调用转发给InvocationHandler接口的invoke方法。
        //代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //设置代理对象
        pih.setTarget(target);
        //动态生成代理类
        DaoService proxy = (DaoService) pih.getProxy();
        proxy.delete();
    }
}
