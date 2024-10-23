package com.lixing.lixingdemo.DesignPatterns.responseChain;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/4
 */
public class BusinessHandler extends Handler {

    @Override
    public void doHandler(Member member) {
        System.out.println("执行校验通过后的业务逻辑代码。。。。。");
    }
}
