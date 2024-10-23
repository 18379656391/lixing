package com.lixing.lixingdemo.juc.AtomicReference;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/27
 * @desc 通过加synchronized锁，采用阻塞式方案，模拟多线程下增加账号金额：模拟有10个人不断地向这个银行账号里打钱，每次都存入10元
 */
public class AtomicReferenceExample2 {

    /**
     * volatile关键字修饰，保证每次对DebitCard对象的引用的写入操作都会被其他线程看到
     */
    static volatile DebitCard debitCard = new DebitCard("zhangsan", 0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread("T-" + i) {
                @Override
                public void run() {
                    synchronized (AtomicReferenceExample2.class) {
                        final DebitCard dc = debitCard;
                        DebitCard newDc = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                        System.out.println(newDc);
                        debitCard = newDc;
                    }
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