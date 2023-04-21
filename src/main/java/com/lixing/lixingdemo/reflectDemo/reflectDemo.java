package com.lixing.lixingdemo.reflectDemo;


import com.alibaba.fastjson.JSON;
import com.lixing.lixingdemo.FactoryBean.Bird;
import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2023-03-03 16:34:46
 */
public class reflectDemo {


    @SneakyThrows
    public static Object processTiger(Object tiger, String source, String target) {
        Field sourceField = tiger.getClass().getDeclaredField(source);
        sourceField.setAccessible(true);
        // 属性值
        Object sourceObject = sourceField.get(tiger);

        Field targetField = tiger.getClass().getDeclaredField(target);
        targetField.setAccessible(true);
        // 属性值
        Object targetObject = targetField.get(tiger);

        // FieldUtils.writeField(tiger, target, Integer.parseInt(String.valueOf(sourceObject)) * 2, true);
        targetField.set(tiger, Integer.parseInt(String.valueOf(sourceObject)) * 2);
        return tiger;
    }

    public static void main(String[] args) {
        // 构造初始数据
        Tiger tiger = new Tiger();
        tiger.setEyeNum(2);

        Tiger tiger1 = (Tiger) processTiger(tiger, "eyeNum", "footNum");
        System.out.println(JSON.toJSONString(tiger1));

    }
}

class Tiger {
    private Integer eyeNum;

    private Integer footNum;

    public Integer getEyeNum() {
        return eyeNum;
    }

    public void setEyeNum(Integer eyeNum) {
        this.eyeNum = eyeNum;
    }

    public Integer getFootNum() {
        return footNum;
    }

    public void setFootNum(Integer footNum) {
        this.footNum = footNum;
    }
}
