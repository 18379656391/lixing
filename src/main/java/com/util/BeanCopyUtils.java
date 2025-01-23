package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lixing.lixingdemo.construct.Exception.BusinessException;
import lombok.SneakyThrows;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2023-02-16 18:49:11
 */
public class BeanCopyUtils {

    @SneakyThrows
    public static <T, V> List<V> listBeanCopy(List<T> sourceList, Class<V> target, Map<String, String> translateMap) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        List<V> resultList = new ArrayList<>();
        for (T source : sourceList) {
            final V v;
            if (source instanceof JSONObject) {
                JSONObject paramObject = JSONObject.parseObject(JSON.toJSONString(source));
                v = JSONObject.parseObject(JSON.toJSONString(SerializationUtils.clone(paramObject)), target);
            } else {
                BeanCopier copier = BeanCopier.create(source.getClass(), target, false);
                v = target.getConstructor().newInstance();
                copier.copy(source, v, null);
                if (Objects.nonNull(translateMap)) {
                    attributeHandle(source, v, translateMap);
                }
            }
            resultList.add(v);
        }
        return resultList;
    }

    private static <T, V> V attributeHandle(T source, V target, Map<String, String> translateMap) throws NoSuchFieldException, IllegalAccessException{
        Iterator<Map.Entry<String, String>> iterator = translateMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            Field sourceField = source.getClass().getDeclaredField(entry.getKey());
            sourceField.setAccessible(true);
            // 属性值
            Object sourceObject = sourceField.get(source);

            Field targetField = target.getClass().getDeclaredField(entry.getValue());
            targetField.setAccessible(true);
            if (targetField.getGenericType().toString().equals(sourceField.getGenericType().toString())) {
                targetField.set(target, sourceObject);
            } else {
                // 后续可通过自定义converter实现
                throw new BusinessException("暂不支持不同类型的属性互转");
            }
        }
        return target;
    }

}
