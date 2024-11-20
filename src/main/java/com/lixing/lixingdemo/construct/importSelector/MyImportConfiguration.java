package com.lixing.lixingdemo.construct.importSelector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/28
 */
@Slf4j
public class MyImportConfiguration {
    static {
        log.info("初始化动态配置类---MyImportConfiguration");
    }
    public void configTest(){
    }
}
