package com.lixing.lixingdemo.asyncDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-12-20 10:35
 * 自定义异步线程池：1.实现AsyncConfigurer接口 2.继承AsyncConfigurerSupport类
 */
@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {
    private Logger log = LoggerFactory.getLogger(AsyncConfiguration.class);

    // 也可以直接重写AsyncConfigurer接口中的getAsyncExecutor方法来指定异步线程池
    @Bean("asyncPoolTaskExecutor")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 获取的是cpu核心线程数也就是计算资源。
        // cpu密集型计算推荐设置线程池核心线程数为N，也就是和cpu的线程数相同，可以尽可能低避免线程间上下文切换。
        // io密集型计算推荐设置线程池核心线程数为2N，但是这个数一般根据业务压测出来的，如果不涉及业务就使用推荐。
        // Runtime.getRuntime().availableProcessors()方法询问jvm，jvm去问操作系统，操作系统去问硬件。。。。。。。
        // 核心线程数
        //executor.setCorePoolSize(Math.max(Runtime.getRuntime().availableProcessors() * 2, 8));
        executor.setCorePoolSize(5);
        //线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        //executor.setMaxPoolSize(executor.getCorePoolSize() * 4);
        executor.setMaxPoolSize(5);
        //许的空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(200);
        //缓存队列
        executor.setQueueCapacity(50);
        //异步方法内部线程名称
        executor.setThreadNamePrefix("async-");

        /**
         * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
         * 通常有以下四种策略：
         * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
         * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，自动重复调用 execute() 方法，直到成功
         */

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    public Executor getAsyncExecutor() {
        return executor();
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return ((ex, method, params) ->
                log.error("线程池异常:{}", method.getName(), ex)
        );
    }

}
