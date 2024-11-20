package com.lixing.lixingdemo.construct.basic;

import com.util.UniqueIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/22
 * 对响应的数据进行封装处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalResultResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    // 在aop结束，序列化前调用
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!(body instanceof ResponseResult)) {
            body = ResponseResult.success(body);
        }
        // 可以对响应参数进行扩展
//        ResponseResult result = (ResponseResult) body;
        return body;
    }
}
