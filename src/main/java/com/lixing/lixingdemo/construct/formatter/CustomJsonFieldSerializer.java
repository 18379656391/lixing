package com.lixing.lixingdemo.construct.formatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.lixing.lixingdemo.annotation.CommaJsonFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/10
 * 自定义序列化器  然后注册
 * 将 List<String>序列化为字符串+分割符号
 * 使用方式：
 * 一：使用@JsonSerializer(using = CustomJsonFieldSerializer.class)注解指定序列化器不需要实现 ContextualSerializer，优先级最高，粒度最细
 */
public class CustomJsonFieldSerializer extends JsonSerializer<List> implements ContextualSerializer{
    private static final long serialVersionUID = 6408813902798291855L;

    private CommaJsonFormat commaJsonFormat;

    public CustomJsonFieldSerializer() {
    }


    /**
     * 序列化
     * @param list
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(List list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String separator = commaJsonFormat.value();
        if (CollectionUtils.isEmpty(list)) {
            jsonGenerator.writeString("");
        } else {
            jsonGenerator.writeString(String.join(separator, list));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (null != beanProperty) {
            CommaJsonFormat annotation = beanProperty.getAnnotation(CommaJsonFormat.class);
            if (Objects.nonNull(annotation)) {
                this.commaJsonFormat = annotation;
                return this;
            }
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
