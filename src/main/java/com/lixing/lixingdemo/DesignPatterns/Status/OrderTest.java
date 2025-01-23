package com.lixing.lixingdemo.DesignPatterns.Status;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2025/1/2
 */
public class OrderTest {

    public static void main(String[] args) {
        Order order = new Order();
        System.out.println(order.getStatus());
        order.nextStatus();
        System.out.println(order.getStatus());
        order.nextStatus();
        System.out.println(order.getStatus());
    }
}
