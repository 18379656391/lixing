package com.lixing.lixingdemo.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    //生成得到代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    //处理代理实例，并返回结果
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        logBegin(method.getName());
        method.invoke(target);
        logEnd(method.getName());
        return null;
    }

    public void logBegin(String msg) {
        System.out.println("调用了" + msg + "方法");
    }

    public void logEnd(String msg) {
        System.out.println("调用" + msg + "方法结束");
    }
}
