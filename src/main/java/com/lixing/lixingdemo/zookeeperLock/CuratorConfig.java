package com.lixing.lixingdemo.zookeeperLock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/2
 * https://blog.csdn.net/qq_41432730/article/details/123389670?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522170666946516800182182127%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=170666946516800182182127&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-2-123389670-null-null.142^v99^pc_search_result_base2&utm_term=zookeeper%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%AE%9E%E8%B7%B5&spm=1018.2226.3001.4187
 */
@Configuration
@ConditionalOnProperty(name = "zookeeper.config.enable", havingValue = "true")
public class CuratorConfig {

    @Value("${zookeeper.address}")
    private String host;

    @Bean
    public CuratorFramework curatorFramework() {
        System.out.println("zookeeper连接地址：" + host);
        CuratorFramework build = CuratorFrameworkFactory.builder().connectString(host).sessionTimeoutMs(15000).connectionTimeoutMs(60000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10)).build();
        build.start();
        return build;
    }
}
