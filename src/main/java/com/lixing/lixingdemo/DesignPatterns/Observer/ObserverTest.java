package com.lixing.lixingdemo.DesignPatterns.Observer;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-18 16:17:22
 */
public class ObserverTest {
    public static void main(String[] args) {
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        WechatServer server = new WechatServer();
        server.registerObserver(user1);
        server.registerObserver(user2);
        server.registerObserver(user3);

        server.setInformation("hello user");

        System.out.println("-----------------------------------");
        server.removeObserver(user3);
        server.setInformation("java is the best language in the world");
    }
}
