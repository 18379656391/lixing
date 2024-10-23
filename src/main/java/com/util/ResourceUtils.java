package com.util;

import com.alibaba.excel.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/19
 */
@Slf4j
public class ResourceUtils {

    public static Resource[] getResources(String path) throws Exception{
        if (StringUtils.isNotBlank(path)) {
            return new PathMatchingResourcePatternResolver().getResources(path);
        }
        return null;
    }

    public static Resource getResource(String path) throws Exception{
        if (StringUtils.isNotBlank(path)) {
            return new PathMatchingResourcePatternResolver().getResource(path);
        }
        return null;
    }

}
