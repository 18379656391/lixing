package com.lixing.lixingdemo.generic;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Apple extends Fruits {
    // ？和 T 都表示不确定的类型，区别在于我们可以对 T 进行操作，但是对 ？,不行
    // T 是一个确定的类型，通常用于泛型类和泛型方法的定义，？是一个不确定的类型，通常用于泛型方法的调用代码和形参，不能用于定义类和泛型方法。
    // T t = operate();
    // ？car = operate(); 不可以

    private Class<?> clazz;

    // private Class<T> clazz; //报错


    // 下界通配符 < ? super E>  E或者E的父类
    private static <T> void add(List<? super T> list, List<T> original) {
        for (T t : original) {
            list.add(t);
        }
    }

    // 上界通配符 < ? extends E> E或者E的子类
    private static int count(List<? extends Fruits> fruits) {
        try {
            int sum = 0;
            for (Fruits f : fruits) {
                sum += f.count();
            }
            return sum;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static <K extends Fruits, E extends Food> E test(K args1, E args2) {
        return args2;
    }


    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        List<Fruits> fruits = new ArrayList<>();
        add(fruits, apples);
        count(apples);


        // 匿名内部类
        // 格式：new 父类/接口名(){重写方法();}
        new Fruits() {
            @Override
            protected int count() {
                System.out.println("匿名内部类重写的count方法执行----");
                return 0;
            }
        }.count();
    }

    @Override
    protected int count() {
        return 1;
    }
}