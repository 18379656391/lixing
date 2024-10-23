package com.lixing.lixingdemo.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/10
 * https://blog.csdn.net/myli92/article/details/119900084
 * 选择配置文件的优先级（由先到后）：
 * 1.classpath下的名为log4j2-test.json 或者log4j2-test.jsn的文件.
 * 2.classpath下的名为log4j2-test.xml的文件.
 * 3.classpath下名为log4j2.json 或者log4j2.jsn的文件.
 * 4.classpath下名为log4j2.xml的文件.
 *
 */
public class LogTest {

    public static void main(String[] args) throws Exception {
        Logger log = LogManager.getLogger(LogTest.class);
        log.trace("trace level");
        log.debug("debug level");
        log.info("info level");
        log.warn("warn level");
        log.error("error level");
        log.fatal("fatal level");
        throw new Exception("text");
    }
}
