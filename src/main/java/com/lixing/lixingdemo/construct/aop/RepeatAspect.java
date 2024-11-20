package com.lixing.lixingdemo.construct.aop;

import com.lixing.lixingdemo.annotation.RepeatCommit;
import com.lixing.lixingdemo.construct.Exception.BusinessException;
import com.lixing.lixingdemo.construct.expand.RedisContainer;
import com.util.ErrorConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.MapAccessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/29
 */
@Component
@Aspect
@Slf4j
public class RepeatAspect {


    @Pointcut("@annotation(com.lixing.lixingdemo.annotation.RepeatCommit)")
    public void handleMethod() {
    }

    @Around("handleMethod()")
    public Object repeatCommitHandle(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // RepeatCommit annotation = method.getAnnotation(RepeatCommit.class);
        RepeatCommit annotation = AnnotationUtils.findAnnotation(method, RepeatCommit.class);
        if (annotation != null) {
            // 默认以类名#方法名作为前缀
            String defaultPrefix = joinPoint.getSignature().getDeclaringTypeName() + "#" + method.getName();
            // map<参数名,参数值>
            Map<String, Object> methodParam = this.getMethodParam(joinPoint);
            repeatValidate(annotation, defaultPrefix, methodParam);
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }

    private void repeatValidate(RepeatCommit annotation, String defaultPrefix, Map<String, Object> paramMap) {
        // 判断注解中是否有指定前缀，没有使用默认前缀：类名#方法名
        String prefix = StringUtils.isBlank(annotation.prefix()) ? defaultPrefix : annotation.prefix();
        // 解析spel语句
        StandardEvaluationContext context = new StandardEvaluationContext(paramMap);
        // context.addPropertyAccessor(new MapAccessor());
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        SpelExpressionParser parser = new SpelExpressionParser();
        try {
            // 通过spel表达式解析出执行的属性名，并通过属性名获取到对应的属性值
            String key = Arrays.stream(annotation.key().split("&&")).map(tempKey ->
                parser.parseExpression(tempKey.trim()).getValue(context, String.class)
            ).collect(Collectors.joining("&"));
            log.info("temp repeat key:{}", key);
           String finalKey = prefix + key;
            // 使用redis中的原子操作setNx命令
            // 这里使用本地map模拟
            if (RedisContainer.containsKey(finalKey)) {
                String msg = annotation.message();
                throw new BusinessException(ErrorConstant.REQUEST_FREQUENTLY, msg);
            } else {
                int time = annotation.time();
                TimeUnit timeUnit = annotation.timeUnit();
                RedisContainer.setKey(finalKey, finalKey, time, timeUnit);
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("校验接口重复提交异常!", e);
            throw e;
        }
    }

    private Map<String, Object> getMethodParam(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> paramMap = new HashMap<>(args.length);
        for (int i = 0; i < args.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }
}
