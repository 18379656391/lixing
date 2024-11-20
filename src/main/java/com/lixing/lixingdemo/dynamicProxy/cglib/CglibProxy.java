package com.lixing.lixingdemo.dynamicProxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/11/6
 */
public class CglibProxy implements MethodInterceptor {
    //被代理的接口
    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        logBegin(method.getName());
        Object result = methodProxy.invokeSuper(o, args);
        logEnd(method.getName());
        return result;
    }

    public void logBegin(String msg) {
        System.out.println("调用了" + msg + "方法");
    }

    public void logEnd(String msg) {
        System.out.println("调用" + msg + "方法结束");
    }
}
