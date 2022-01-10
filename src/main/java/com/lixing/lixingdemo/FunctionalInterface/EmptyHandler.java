package com.lixing.lixingdemo.FunctionalInterface;

import java.util.function.Consumer;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-11-29 15:25
 */
@FunctionalInterface
public interface EmptyHandler<T extends Object> {
    public abstract void emptyHandle(Consumer<? super T> present, Runnable empty);
}
