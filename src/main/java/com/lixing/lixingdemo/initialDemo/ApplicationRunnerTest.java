package com.lixing.lixingdemo.initialDemo;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/16
 * 与CommandLineRunner的区别
 * 虽然ApplicationRunner和CommandLineRunner都可以在应用启动后执行代码，但是它们之间有以下主要区别：
 * 1.参数类型：ApplicationRunner使用ApplicationArguments对象来提供更详细的命令行参数信息，而CommandLineRunner只提供字符串数组String[]。
 * 2.执行顺序：两者执行顺序都依赖于Spring的Bean加载顺序，可以通过@Order注解来控制，但是如果没有特别指定，它们的执行顺序是不确定的。
 */
@Component
@Order(2)
public class ApplicationRunnerTest implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("("+Thread.currentThread().getName()+")9999-->ApplicationRunner代码执行---");
        // 检查是否存在一个名为"debug"的选项
        System.out.println("ApplicationArguments args--" + JSON.toJSONString(args));
        boolean debug = args.containsOption("debug");

        // 获取所有非选项参数
        List<String> nonOptionArgs = args.getNonOptionArgs();

        // 输出调试信息
        if (debug) {
            System.out.println("Debug mode is on.");
        } else {
            System.out.println("Debug mode is off.");
        }

        // 输出非选项参数
        System.out.println("Non-option arguments: " + nonOptionArgs);

    }
}
