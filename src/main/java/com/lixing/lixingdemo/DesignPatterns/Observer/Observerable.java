package com.lixing.lixingdemo.DesignPatterns.Observer;


/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-18 15:30:40
 * 被观察的对象,主体对象Subject
 */
public interface Observerable {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObserver();
}
