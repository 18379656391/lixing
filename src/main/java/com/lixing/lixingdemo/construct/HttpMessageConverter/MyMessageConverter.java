package com.lixing.lixingdemo.construct.HttpMessageConverter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/29
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<MyEntity> {

    /**
     * 构造器，定义lixing消息类型由当前转换器处理
     */
    public MyMessageConverter() {
        // 自定义类型 application/lixing
        super(new MediaType("application", "lixing", StandardCharsets.UTF_8));
    }
    @Override
    protected boolean supports(Class<?> clazz) {
        // 表明当前处理器只处理MyEntity类型的参数
        return MyEntity.class.isAssignableFrom(clazz);
    }

    @Override
    protected MyEntity readInternal(Class<? extends MyEntity> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
        String[] tempArr = temp.split("-");
        return new MyEntity(tempArr[0], tempArr[1]);
    }

    /**
     * 处理response
     * @param myEntity
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(MyEntity myEntity, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String result = "special return" + myEntity.getName() + "-" + myEntity.getAddress();
        outputMessage.getBody().write(result.getBytes());
    }
}
