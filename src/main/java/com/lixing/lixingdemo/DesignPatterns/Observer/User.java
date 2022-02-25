package com.lixing.lixingdemo.DesignPatterns.Observer;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-18 16:10:14
 */
public class User implements Observer {

    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        this.message = message;
        read();
    }

    public void read() {
        System.out.println(name + "收到公众号推送信息" + message);
    }
}
