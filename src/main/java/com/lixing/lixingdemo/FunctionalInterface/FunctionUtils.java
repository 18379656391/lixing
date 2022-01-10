package com.lixing.lixingdemo.FunctionalInterface;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-11-29 13:55
 */
public class FunctionUtils {

    public static ThrowExceptionFunction isTrue(boolean flag) {
        return (exceptionMsg) ->{
            if (flag){
                throw new RuntimeException(exceptionMsg);
            }
        };
    }

    public static BranchHandler select(boolean flag) {
        return ((trueBranch, falseBranch) -> {
            if (flag) {
                trueBranch.run();
            }else {
                falseBranch.run();
            }
        });
    }


    public static EmptyHandler emptyHandler(String str){
        return (consumer,runnable) -> {
            if (null == str || str.length() == 0) {
                runnable.run();
            }else {
                consumer.accept(str);
            }
        };
    }
}
