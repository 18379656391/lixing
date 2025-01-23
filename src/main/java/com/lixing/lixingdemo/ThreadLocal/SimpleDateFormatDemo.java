package com.lixing.lixingdemo.ThreadLocal;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2023-03-02 13:53:24
 * ThreadLocal用途
 * 1.保存线程上下文信息，在任意需要的地方可以获取（spring事务，用ThreadLocal存储connection）
 * 2.线程安全的，避免某些情况需要考虑线程安全必须同步带来的性能损失
 * 3.做数据隔离，如果创建一个ThreadLocal变量，访问这个变量的每个线程都会有这个变量的副本，操作的是自己本地内存中的变量，从而规避线程安全问题
 *
 * 线程不安全原因：内部使用的是父类DateFormat的calendar实例变量，在Calendar.establish()方法中会先后调用cal.clear()和cal.set();由于Calendar内部并没有线程安全机制并且对象多线程共享，所以存在线程安全问题
 * parse()和format()方法都存在线程安全问题
 *解决方法：1.局部变量 2.ThreadLocal 3.加锁 4.DateTimeFormatter
 */
public class SimpleDateFormatDemo {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static final ThreadLocal<SimpleDateFormat> formatThreadLocal = ThreadLocal.withInitial(() ->new SimpleDateFormat("yyyy-MM-dd"));

    public static String calculateDate(Date date) {
        return format.format(date);
    }

    @SneakyThrows
    public static Date parseDate(String date) {
        return format.parse(date);
    }

    @SneakyThrows
    public static Date parseDateThreadLocal(String date) {
        return formatThreadLocal.get().parse(date);
    }

    public static void main(String[] args) {
        // 不做线程数据隔离，引发的线程安全场景 Calendar.clear()  Calendar.add()
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "dateParse---" + parseDate("2022-03-02"));
                }
            }, "ThreadNO:" + String.valueOf(i)).start();
        }

        // 数据隔离线程安全场景
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "ThreadLocal dateParse---" + parseDateThreadLocal("2022-03-02"));
                }
            }, "ThreadNO:" + String.valueOf(i)).start();
        }


    }
}
