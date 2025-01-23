package com.lixing.lixingdemo.juc.AtomicReference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/27
 * @desc 模拟多线程下增加账号金额：模拟有10个人不断地向这个银行账号里打钱，每次都存入10元
 * https://blog.csdn.net/wyaoyao93/article/details/115868025
 * 基于CAS算法的原子操作
 * AtomicReference的方法：
 * 1.compareAndSet(V expect, V update)  原子性地更新AtomicReference内部的value值，其中expect代表当前AtomicReference的value值，update则是需要设置的新引用值。
 * 该方法会返回一个boolean的结果，当expect和AtomicReference的当前值不相等时，修改会失败，返回值为false，若修改成功则会返回true。
 * 2.getAndSet(V newValue)   原子性地更新AtomicReference内部的value值，并且返回AtomicReference的旧值。
 * 3.getAndUpdate(UnaryOperator<V> updateFunction)  原子性地更新value值，并且返回AtomicReference的旧值，该方法需要传入一个Function接口。
 * 4.updateAndGet(UnaryOperator<V> updateFunction)  原子性地更新value值，并且返回AtomicReference更新后的新值，该方法需要传入一个Function接口。
 * 5.getAndAccumulate(V x, BinaryOperator<V> accumulatorFunction)  原子性地更新value值，并且返回AtomicReference更新前的旧值。该方法需要传入两个参数，第一个是更新后的新值，第二个是BinaryOperator接口。
 * 6.accumulateAndGet(V x, BinaryOperator<V> accumulatorFunction)  原子性地更新value值，并且返回AtomicReference更新后的新值。该方法需要传入两个参数，第一个是更新的新值，第二个是BinaryOperator接口。
 * 7.get()   获取AtomicReference的当前对象引用值。
 * 8.set(V newValue)   设置AtomicReference最新的对象引用值，该新值的更新对其他线程立即可见。
 * 9.lazySet(V newValue)   设置AtomicReference的对象引用值。
 */
public class AtomicReferenceExample3 {


    // 保证原子操作
    static AtomicReference<DebitCard> debitCardRef = new AtomicReference<>(new DebitCard("zhangsan", 0));

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread("T-" + i){
                @Override
                public void run(){
                    // 获取AtomicReference的当前值
                    final DebitCard dc = debitCardRef.get();
                    // 基于AtomicReference的当前值创建一个新的DebitCard
                    DebitCard newDc = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                    // 基于CAS算法，尝试更新AtomicReference的当前值，只有当前的引用和AtomicReference的引用相同，才会更新成功
                    if (debitCardRef.compareAndSet(dc, newDc)) {
                        System.out.println(newDc);
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