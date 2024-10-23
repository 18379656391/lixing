package com.lixing.lixingdemo.cache;

import com.alibaba.fastjson.JSONObject;
import com.lixing.lixingdemo.initialDemo.User;
import com.lixing.lixingdemo.pojo.Student;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/8/13
 */
@RestController
public class CacheController {

    @Resource
    private CacheUtil cacheUtil;

    @PostMapping(value = "/cacheTest")
    public Student cacheTest(@RequestBody JSONObject param){
        return cacheUtil.getKey(param.getString("key"));
    }



}
