package com.lixing.lixingdemo.DesignPatterns.Observer;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-18 15:41:30
 * 当被观察者调用notifyObservers()方法时，观察者的update方法会被回调
 *
 * */
public interface Observer {
    public void update(String message);
}
