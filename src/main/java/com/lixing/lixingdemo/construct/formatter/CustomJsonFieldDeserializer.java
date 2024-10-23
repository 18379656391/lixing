package com.lixing.lixingdemo.construct.formatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.lixing.lixingdemo.annotation.CommaJsonFormat;
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
 * 自定义反序列化器 然后注册
 * 将 字符串分割反序列化为List<String>
 *  使用方式：
 *  一：使用@JsonDeserialize(using = CustomJsonFieldDeserializer.class)注解指定反序列化器不需要实现 ContextualDeserializer，优先级最高，粒度最细
 */
public class CustomJsonFieldDeserializer extends JsonDeserializer<List> implements ContextualDeserializer{
    private static final long serialVersionUID = 1168883448563468534L;

    private CommaJsonFormat commaJsonFormat;


    public CustomJsonFieldDeserializer() {
    }

    /**
     * 反序列化
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String separator = commaJsonFormat.value();
        List<String> returnList = new ArrayList<>();
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return returnList;
        } else if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            String text = jsonParser.getText();
            if (StringUtils.isNotBlank(text) && text.contains(separator) && StringUtils.isNotBlank(separator)) {
                returnList.addAll(Arrays.asList(text.split(separator)));
            } else {
                returnList.add(text);
            }
        }
        return returnList;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        if (null != beanProperty) {
            CommaJsonFormat annotation = beanProperty.getAnnotation(CommaJsonFormat.class);
            if (Objects.nonNull(annotation)) {
                this.commaJsonFormat = annotation;
                return this;
            }
        }
        JavaType type = deserializationContext.getContextualType() != null ? deserializationContext.getContextualType() : beanProperty.getMember().getType();
        return deserializationContext.findContextualValueDeserializer(type, beanProperty);
    }
}
