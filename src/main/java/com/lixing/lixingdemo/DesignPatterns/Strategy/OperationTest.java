package com.lixing.lixingdemo.DesignPatterns.Strategy;

import com.lixing.lixingdemo.construct.Exception.BusinessException;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/12/3
 */
public class OperationTest {

    public static void main(String[] args) {
        Operation tempOperation = OperationFactory.getOperation("add").orElseThrow(() -> new BusinessException("不合规的操作类型！"));
        int result = tempOperation.execute(1, 2);
        System.out.println("result:" + result);
    }
}
