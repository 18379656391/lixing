package com.lixing.lixingdemo.juc.AtomicReference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom.*;
import java.util.concurrent.atomic.AtomicMarkableReference;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/27
 * @desc 模拟多线程下增加账号金额：模拟有10个人不断地向这个银行账号里打钱，每次都存入10元
 * https://blog.csdn.net/wyaoyao93/article/details/115868025
 *
 * 结果会出现多个线程输出同样金额的情况：
 * 原因：volatile关键字不保证原子性，对对象引用的修改包含两个步骤：
 * 1. 读取对象的引用
 * 2. 修改对象的引用
 * 这两个步骤单独来说都是原子性操作，但是组合起来就无法保证原子性了 (synchronized关键字既可以保证原子性，又可以保证可见性)
 * 解决方案：
 * 1.加锁 AtomicReferenceExample2
 * 2.使用AtomicReference的非阻塞解决方案 AtomicReferenceExample3，存在ABA问题（解决 1.加版本号或者标记（AtomicMarkableReference） 2.加时间戳（带有时间戳的CAS操作类AtomicStampedReference））
 */
public class AtomicReferenceExample1 {

    /**
     * volatile关键字修饰，保证每次对DebitCard对象的引用的写入操作都会被其他线程看到
     */
    static volatile DebitCard debitCard = new DebitCard("zhangsan", 0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread("T-" + i){
                @Override
                public void run(){
                    final DebitCard dc = debitCard;
                    DebitCard newDc = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                    System.out.println(newDc);
                    debitCard = newDc;
                    try {
                        TimeUnit.MILLISECONDS.sleep(current().nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}