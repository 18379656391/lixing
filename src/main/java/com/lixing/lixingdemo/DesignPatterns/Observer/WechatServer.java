package com.lixing.lixingdemo.DesignPatterns.Observer;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-18 15:51:21
 * 被观察者，模拟微信公众号服务
 */
public class WechatServer implements Observerable{

    private List<Observer> list;
    private String message;

    public WechatServer(){
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (CollectionUtils.isNotEmpty(list)) {
            list.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        list.forEach(observer -> {
            observer.update(message);
        });
    }

    public void setInformation(String s) {
        this.message = s;
        System.out.println("微信服务更新消息:" + s);
        // 消息更新，通知所有观察者
        notifyObserver();
    }
}
