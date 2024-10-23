package com.lixing.lixingdemo.asyncDemo.Future;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-09 15:27
 * completableFuture使用场景：1.创建异步任务 2.简单任务异步回调 3.多个任务组合处理
 * completableFuture默认使用ForkJoinPool.commonPool()
 * 两种创建异步任务的方法  1.supplyAsync()(支持返回值)  2.runAsync()(不关注返回值)
 *
 * 将多个异步任务合并，主线程等待全部异步线程执行完
 * List<CompletableFuture> futures = handSpecialDownLoadFile(specialMap, input, excelSourceFilePath, pdfSourceFilePath); -- list.add(tempFuture);
 * CompletableFuture<Void> future = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
 * future.join();
 *     
 */
@Component
public class CompletableFutureTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private ThreadPoolTaskExecutor executor;

    @PostConstruct
    private void init(){
        executor = applicationContext.getBean("asyncPoolTaskExecutor", ThreadPoolTaskExecutor.class);
    }

    // 创建异步任务
    @SneakyThrows
    public void CompletableFutureExecute(){
        UserInfoService service = new UserInfoService();
        long userId = 666L;
        long startTime = System.currentTimeMillis();

        // 调用用户服务获取用户基本信息
        CompletableFuture<UserInfo> completableUserInfoFuture = CompletableFuture.supplyAsync(() -> service.getUserInfo(userId), executor);

        Thread.sleep(300); // 模拟主线程其他操作耗时

        CompletableFuture<MedalInfo> completableMedalInfoFuture = CompletableFuture.supplyAsync(() -> service.getMedalInfo(userId), executor);

        UserInfo userInfo = completableUserInfoFuture.join();
        MedalInfo medalInfo = completableMedalInfoFuture.join();

        System.out.println(userInfo);
        System.out.println(medalInfo);

        System.out.println("总耗时:" + (System.currentTimeMillis() - startTime) + "ms");

    }

    // 任务异步回调

    /**
     *  * 异步回调方法:
     *  * 1.thenRun() || thenRunAsync() 不关心上一个任务的执行返回结果，无传参，无返回值  --当执行第一个任务的时候传入自定义线程池，thenRun执行第二个任务时候共用第一个任务的线程池，thenRunAsync则使用默认的ForkJoinPool
     *  * 2.thenAccept() || thenAcceptAsync() 依赖上一个任务的结果，有传参，无返回值
     *  * 3.thenApply() || thenApplyAsync() 依赖上一个任务的结果，有返回值
     *  * 4.exceptionally() 某个任务执行异常时，执行的回调方法
     *  * 5.whenComplete() 某个任务执行完成后，执行的回调方法，无返回值
     *  * 6.handle() 某个任务执行完成后，执行的回调方法，有返回值
     */
    // thenRun:做完第一个任务后，再做第二个任务
    @SneakyThrows
    public void FutureThenRunTest(){
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "先执行第一个CompleteFuture方法任务");
            return "test1";
        }, executor);
//        System.out.println(Thread.currentThread().getName() +"主线程开始睡眠2s....");
//        Thread.sleep(2000);
//        System.out.println(Thread.currentThread().getName() +"主线程结束睡眠....");
        CompletableFuture thenRunFuture = orgFuture.thenRun(() -> {
            System.out.println(Thread.currentThread().getName() +"接着执行第二个任务");
        });
        System.out.println(thenRunFuture.get());
    }

    @SneakyThrows
    public void FutureThenAcceptTest(){
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("原始CompletableFuture方法任务");
            return "test1";
        }, executor);

        CompletableFuture thenAcceptFuture = orgFuture.thenAccept((a) ->{
            if ("test1".equals(a)) {
                System.out.println("入参成功传入第二个任务");
            }

            System.out.println("执行第二个任务，参数：" + a);
        });

        System.out.println(thenAcceptFuture.get());
    }

    @SneakyThrows
    public void FutureThenApplyTest(){
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("原始CompletableFuture方法任务");
            return "test1";
        });

        CompletableFuture<String> thenApplyFuture = orgFuture.thenApply((a) ->{
            if ("test1".equals(a)) {
                return "入参成功传入第二个任务";
            }

            return "执行第二个任务，参数：" + a;
        });

        System.out.println(thenApplyFuture.get());
    }

    @SneakyThrows
    public void FutureExceptionTest(){
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "原始CompletableFuture方法任务");
            throw new RuntimeException();
        });

        CompletableFuture<String> thenApplyFuture = orgFuture.exceptionally((e) ->{
            e.printStackTrace();

            return "业务执行异常";
        });

        System.out.println(thenApplyFuture.get());
    }

    @SneakyThrows
    public void FutureWhenTest(){
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "原始CompletableFuture方法任务");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "test1";
        }, executor);

        CompletableFuture<String> rstFuture = orgFuture.whenComplete((a,throwable) ->{
            System.out.println(Thread.currentThread().getName() + "上个任务执行完成，并且传入参数:" + a);
            if ("test1".equals(a)) {
                System.out.println("入参正确传入");
            }
            System.out.println("run correctly");
        });

        System.out.println(rstFuture.get());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
