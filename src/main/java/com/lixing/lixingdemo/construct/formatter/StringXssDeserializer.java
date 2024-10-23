package com.lixing.lixingdemo.construct.formatter;

import cn.hutool.http.HtmlUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.owasp.esapi.ESAPI;

import java.io.IOException;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/22
 */
public class StringXssDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String resource = jsonParser.getText().trim();
        return ESAPI.encoder().encodeForHTML(resource);
        //return HtmlUtil.cleanHtmlTag(resource)
    }

    @Override
    public Class<?> handledType() {
        return String.class;
    }
}
