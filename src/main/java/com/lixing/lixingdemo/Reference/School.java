package com.lixing.lixingdemo.Reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2023-03-01 14:07:33
 * 什么是弱引用？
 * 如果一个对象具有弱引用，那么当GC线程扫描的过程中一旦发现某个对象只有弱引用而且不存在强引用时不管当前内存空间足够与否GC都会回收它的内存。由于垃圾回收器是一个优先级比较低的线程，所以不一定会很快发现那些只有弱引用的对象，
 * 为了房子内存溢出，在处理一些占用内存大而且生命周期较长的对象的时候，可以尽量使用软引用和弱引用
 *
 * 强引用：
 *      类似 Object obj = new Object()这类的引用，只要强引用还存在，GC永远不会回收掉被引用的对象
 * 软引用：SoftReference
 *      描述有些还有用但非必须的对象。在系统将要发生内存溢出的时候，将会把这些对象列进回收范围进行二次回收；如果内存还不够，跑异常
 * 弱引用：WeakReference
 *      描述非必须对象；下一次GC回收，无论内存是否足够，都会回收掉自卑弱引用关联的对象
 * 虚引用：PhantomReference
 *      在这个对象被收集器回收时收到一个系统通知；
 */
public class School extends WeakReference<Teacher> {

    String name = "清华大学";

    public School(Teacher teacher) {
        super(teacher);
    }

    public School(Teacher teacher, String name) {
        super(teacher);
        this.name = name;
    }
}

class Teacher {
    String name;

    Teacher(){}

    Teacher(String name) {
        this.name = name;
    }
}

class Test{
    public static void main(String[] args) {
        // 我们把强引用比作老师的工牌，而代课老师没有工牌
        // 张三的定义就是强引用，teacher就是他的工牌（强引用）
        Teacher teacher = new Teacher("张三");
        // 假设张三生病了，从外面青睐了李四教授 因为是临时请过来的，所以没有工牌 直接new（）弱引用
        School school = new School(new Teacher("李四"));
        // 通过WeakReference的get()方法获取引用教师的信息
        System.out.println("current teacher:" + school.get().name);
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (school.get() == null) {
            System.out.println("李四老师因为是外聘老师，已经上完课回家了");
        } else {
            System.out.println("李四老师竟然还想继续上体育课，怎么可能");
        }

        System.out.println("学校名称" + school.name);
        school = new School(teacher);
        System.out.println("current teacher" + school.get().name);
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (school.get() == null) {
            System.out.println("张三老师竟然被开除了");
        } else {
            System.out.println("张三老师还在");
        }

    }
}
