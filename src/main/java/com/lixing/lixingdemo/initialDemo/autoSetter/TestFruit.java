package com.lixing.lixingdemo.initialDemo.autoSetter;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-06-30 19:18:00
 */
@Component
public class TestFruit implements InitializingBean {

    @Resource
    private List<Fruit> fruitList;

    @Resource
    private Map<String, Fruit> fruitMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("fruit map" + JSON.toJSONString(fruitMap));
        for (Fruit fruit : fruitList) {
            fruit.sell();
        }
    }
}
