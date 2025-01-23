package com.lixing.lixingdemo.DesignPatterns.Strategy;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/12/3
 */
public class AddOperation implements Operation{
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}
