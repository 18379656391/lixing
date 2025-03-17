package com.lixing.lixingdemo.jvm;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2025/2/8
 * 分析研究jvm的内存逃逸分析

 *
 * jvm参数
 * -XX:+PrintGC 在控制台输出GC回收日志
 * -XX:-DoEscapeAnalysis 关闭逃逸分析
 *
 * 默认开启逃逸分析：程序GC只输出来了两条，说明GC回收只回收了两次。JDK1.7后默认开启了逃逸分析，JVM会分析test()中的str对象不会出现逃逸的情况，所以就会把str对象用到的空间分配到了栈上，
 * 方法执行结束后内存空间随着弹栈而销毁，所以就会减少GC的压力。
 *
 * 关闭逃逸分析后：会不断地打印GC回收日志，说明GC会一直执行，因为str对象会一直存在堆上，所以就会一直增加GC的次数。
 */
public class MemoryAnalysis {

    public static void main(String[] args) {
        while (true) {
            test();
        }
    }
    public static void test() {
        String str = new String();
    }
}
