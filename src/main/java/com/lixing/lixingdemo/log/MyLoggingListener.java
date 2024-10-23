package com.lixing.lixingdemo.log;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/16
 * 通过监听器，实现log4j2.xml使用application.yml中的属性
 * 原理：读取yml中的配置，然后设置到System.setProperty中
 */
public class MyLoggingListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment env = event.getEnvironment();
        Map<String, Object> systemEnvironment = env.getSystemEnvironment();
        System.out.println("systemEnvironment----" + JSON.toJSONString(systemEnvironment));
        Map<String, Object> systemProperties = env.getSystemProperties();
        System.out.println("systemProperties----" + JSON.toJSONString(systemProperties));
        String filePath = env.getProperty("logFilePath");
        if (StringUtils.isNotBlank(filePath)) {
            System.out.println("================logFilePath:" + filePath);
            System.setProperty("logFilePath", filePath);
        }
    }

    @Override
    public int getOrder() {
        // 当前监听器的启动顺序需要在日志配置监听器的前面，所以此处减 1
        return LoggingApplicationListener.DEFAULT_ORDER - 1;
    }
}
