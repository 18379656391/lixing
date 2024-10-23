package com.lixing.lixingdemo.construct.Exception;

import cn.hutool.core.io.resource.ResourceUtil;
import com.util.CommonConstant;
import com.util.ResourceUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/19
 */
@Component
@Slf4j
public class ErrorCodeContainer implements InitializingBean, ApplicationListener<ContextRefreshedEvent> {

    public static final String ERROR_CONFIG = "classpath:errorContainer-*.properties";

    public static final Map<String, String> ERROR_INFO_CONTAINER = new ConcurrentHashMap<>();

    private static Resource[] locations;

    @Override
    public void afterPropertiesSet() throws Exception {
        synchronized (ErrorCodeContainer.class) {
            if (null == locations) {
                locations = ResourceUtils.getResources(ERROR_CONFIG);
            }
            if (locations.length > 0) {
                for (Resource resource : locations) {
                    loadErrorInfo(resource);
                }
            }else {
                log.info("没有找到需要装载的异常配置信息---");
            }
        }
    }

    private static void loadErrorInfo(Resource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            String fileName = resource.getFilename();
            Properties properties = new Properties();
            properties.load(inputStream);
            for (Object key : properties.keySet()) {
                ERROR_INFO_CONTAINER.put((String) key, properties.getProperty((String) key));
            }
            log.info("load error properties file ->【{}】 success!", fileName);
        } catch (IOException e) {
            log.error("load error properties file failed!", e);
        }
    }

    public static final String getErrorMessage(String key) {
        return ERROR_INFO_CONTAINER.getOrDefault(key, key);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        StringBuilder sb = new StringBuilder();
        for (String key : ERROR_INFO_CONTAINER.keySet()) {
            sb.append(key).append("=").append(ERROR_INFO_CONTAINER.get(key)).append(CommonConstant.NEW_LINE);
        }
        log.info("\n异常配置信息组装完成---\n{}", sb);
    }
}
