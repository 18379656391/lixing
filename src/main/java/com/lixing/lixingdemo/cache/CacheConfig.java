package com.lixing.lixingdemo.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 使用caffeine框架作为二级缓存缓存数据（可以用作用户不敏感的数据，比如菜单数据）
 * 二级缓存能够提升性能，但会存在数据不一致性的问题，如果数据更新了，不能及时刷新缓存；如果有多台服务器节点，可能存在各个节点上数据不一样的情况。
 * https://blog.csdn.net/u010361276/article/details/132016393
 * @author lixing41189
 * @version 1.0
 * @date 2024/8/13
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                //最后一次写入后经过固定时间过期
                .expireAfterWrite(100, TimeUnit.SECONDS)
                //最后一次访问后经过固定时间过期
                .expireAfterAccess(100, TimeUnit.SECONDS)
                // 初始的缓存大小
                .initialCapacity(5)
                //缓存的最大条数
                .maximumSize(10);
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}
