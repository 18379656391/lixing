package com.lixing.lixingdemo.TransientDemo;

import java.io.*;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-18 16:32
 * transient只能用来修饰成员变量（field），被transient修饰的成员变量不参与序列化过程。
 * Java中的对象如果想要在网络上传输或者存储在磁盘时，就必须要序列化。Java中序列化的本质是Java对象转换为字节序列。但是在序列化的过程中，可以允许被序列对象中的某个成员变量不参与序列化，即该对象完成序列化之后，
 * 被transient修饰的成员变量会在字节序列中消失。
 * 1.静态成员变量是不能被序列化的，不管有没有transient关键字
 */
public class TransientDemo {

    public static void main(String[] args) {
        Animal keji = Animal.builder().animalType("dog").animalName("keji").animalColor("brown").build();
        System.out.println("序列化前" + keji.toString());
        try {
            // 将对象序列化到byte数组
            byte[] bytes;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(keji);
            outputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
            outputStream.close();
            byteArrayOutputStream.close();

            // 数组转对象
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            Animal kejiSeri = (Animal) inputStream.readObject();
            System.out.println("序列化后" + kejiSeri.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
