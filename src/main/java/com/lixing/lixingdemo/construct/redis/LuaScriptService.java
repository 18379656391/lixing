package com.lixing.lixingdemo.construct.redis;

import org.apache.commons.math3.analysis.function.Abs;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/11/22
 */
@Service
public class LuaScriptService {

    private static final DefaultRedisScript<String> script;

    @Resource
    private StringRedisTemplate redisTemplate;

    static {
        script = new DefaultRedisScript<>();
        script.setResultType(String.class);
        // classpath:myScriptTest.lua
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/myLua.lua")));
    }

    public String executeLua() {
        List<String> keys = new ArrayList<>();
        keys.add("key1");
        Object[] args = new Object[]{"10","50"};
        String execute = redisTemplate.execute(script, keys, args);
        return execute;
    }

}
