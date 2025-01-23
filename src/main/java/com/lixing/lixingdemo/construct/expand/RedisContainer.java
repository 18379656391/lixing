package com.lixing.lixingdemo.construct.expand;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/26
 * 模拟redis
 */
@Component
@Slf4j
public final class RedisContainer implements ApplicationContextAware {

    private static final Map<String, String> REDIS_MAP = new ConcurrentHashMap<>();

    private static ApplicationContext applicationContext;
    private static ThreadPoolTaskExecutor executor;
    public static Boolean existsRedisConfig = Boolean.FALSE;

    @PostConstruct
    private void init() {
        executor = applicationContext.getBean("asyncPoolTaskExecutor", ThreadPoolTaskExecutor.class);
    }

    public static void setKey(String key, String value, int time, TimeUnit timeUnit) {
        if (existsRedisConfig) {
            getRedisTemplate().opsForValue().set(key, value, time, timeUnit);
        } else {
            REDIS_MAP.put(key, value);
            CompletableFuture.runAsync(() -> {
                        try {
                            TimeUnit.MILLISECONDS.sleep(timeUnit.toMillis(time));
                            REDIS_MAP.remove(key);
                            log.info("redis key 删除成功! key:{}", key);
                        } catch (InterruptedException e) {
                            log.error("redis key 删除异常!", e);
                        }
                    }
                    , executor);
        }
        log.info("redis key 设置完成！key:{}", key);
    }

    public static boolean containsKey(String key) {
        if (existsRedisConfig) {
            Object o = getRedisTemplate().opsForValue().get(key);
            return !Objects.isNull(o);
        }
        return REDIS_MAP.containsKey(key);
    }

    public static RedisTemplate getRedisTemplate(){
        return applicationContext.getBean("redisTemplate", RedisTemplate.class);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, RedisTemplate> typeMap = applicationContext.getBeansOfType(RedisTemplate.class);
        if (!typeMap.isEmpty() && Objects.nonNull(typeMap.get("redisTemplate"))) {
            existsRedisConfig = Boolean.TRUE;
            log.info("存在redis配置--------，进行redis操作----");
            return;
        }
        log.info("不存在redis配置--------，使用Map进行redis模拟操作-----");
    }
}
