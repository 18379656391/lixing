package com.lixing.lixingdemo.asyncDemo.Future;

import com.lixing.lixingdemo.initialDemo.User;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-09 13:47
 * future获取结果是通过阻塞(future.get())或者轮询(future.isDone())的方式得到任务的结果，阻塞的方式和异步编程的设计理念相违背，轮询的方式会耗费无谓的CPU资源
 * 因此JDK8设计出CompletableFuture,基于观察者模式类似的机制，可以让任务执行完后通知监听的一方
 */
@Component
public class FutureTest {
    @Resource
    private ApplicationContext applicationContext;

    @SneakyThrows
    public void FutureExecute(){
        ThreadPoolTaskExecutor executor = applicationContext.getBean("asyncPoolTaskExecutor", ThreadPoolTaskExecutor.class);
        UserInfoService service = new UserInfoService();
        long userId = 666L;
        long startTime = System.currentTimeMillis();

        // 调用用户服务获取用户基本信息
        FutureTask<UserInfo> userInfoFutureTask = new FutureTask<>(new Callable<UserInfo>() {
            @Override
            public UserInfo call() throws Exception {
                return service.getUserInfo(userId);
            }
        });
        executor.submit(userInfoFutureTask);

        Thread.sleep(300); // 模拟主线程其他操作耗时

        FutureTask<MedalInfo> medalInfoFutureTask = new FutureTask<>(new Callable<MedalInfo>() {
            @Override
            public MedalInfo call() throws Exception {
                return service.getMedalInfo(userId);
            }
        });
        executor.submit(medalInfoFutureTask);

        UserInfo userInfo = userInfoFutureTask.get();
        MedalInfo medalInfo = medalInfoFutureTask.get();

        System.out.println("总耗时:" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
