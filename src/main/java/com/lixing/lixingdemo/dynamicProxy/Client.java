package com.lixing.lixingdemo.dynamicProxy;


import javax.annotation.Resource;

public class Client {

    public static void main(String[] args) {
        //真实角色
        DaoServiceImpl target = new DaoServiceImpl();
        //代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //设置代理对象
        pih.setTarget(target);
        //动态生成代理类
        DaoService proxy = (DaoService) pih.getProxy();
        proxy.delete();
    }
}
