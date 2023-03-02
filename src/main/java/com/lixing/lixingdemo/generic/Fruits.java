package com.lixing.lixingdemo.generic;

import org.springframework.stereotype.Component;

@Component
public abstract class Fruits extends Food {
    protected abstract int count();
}
