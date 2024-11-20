package com.lixing.lixingdemo.construct.aop;

import com.alibaba.fastjson.JSON;
import com.lixing.lixingdemo.annotation.NotPrintLog;
import com.lixing.lixingdemo.construct.Exception.BusinessException;
import com.lixing.lixingdemo.construct.basic.ResponseResult;
import com.lixing.lixingdemo.construct.expand.ServiceContainer;
import com.util.UniqueIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/25
 * @Around：环绕通知，此注解标注的通知方法在目标方法前、后都被执行
 * @Before：前置通知，此注解标注的通知方法在目标方法前被执行
 * @After ：后置通知，此注解标注的通知方法在目标方法后被执行，无论是否有异常都会执行
 * @AfterReturning ： 返回后通知，此注解标注的通知方法在目标方法后被执行，有异常不会执行
 * @AfterThrowing ： 异常后通知，此注解标注的通知方法发生异常后执行
 */
@Component
@Aspect
@Slf4j
public class ControllerAopHandler {

    // *(..)表示匹配所有公共方法
    @Pointcut("execution(public * com.lixing.lixingdemo.controller.*Controller.*(..))")
    public void handleController(){
    }

    @Around(("handleController()"))
    public Object aopHandle(ProceedingJoinPoint joinPoint) throws Throwable{
        if (isAopIgnore(joinPoint)) {
            return joinPoint.proceed(joinPoint.getArgs());
        }
        String requestId = UniqueIdGenerator.getInstance().nextId().toString();
        boolean isLog = true;
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        if (null != method.getAnnotation(NotPrintLog.class)) {
            isLog = false;
        }
        List<Object> logList = Lists.newArrayList();
        logList.add(joinPoint.getTarget().getClass().getName());
        // 类名
        // logList.add(joinPoint.getSignature().getDeclaringTypeName());
        logList.add(joinPoint.getSignature().getName());
        logList.add(JSON.toJSONString(joinPoint.getArgs()));
        ThreadContext.put("request_id", requestId);
        writeLog(isLog,"执行controller方法调用，类名:[{}],方法名:[{}],调用参数:[{}],", logList.toArray());
        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed(joinPoint.getArgs());
            long endTime = System.currentTimeMillis();
            if (result instanceof ResponseResult) {
                ((ResponseResult<?>) result).setTraceId(requestId);
                logList.add(JSON.toJSONString(result));
            } else {
                logList.add("不打印返回值");
            }
            logList.add(endTime - startTime +  "ms");
            return result;
        } catch (Throwable throwable) {
            if (throwable instanceof BusinessException) {
                BusinessException exception = (BusinessException) throwable;
                writeLog(isLog,"切面记录到业务异常信息：errorCode-【{}】,errorMsg:【{}】", exception.getErrorCode(),exception.getErrorMessage());
            }
            throw throwable;
        }finally {
            // todo 记录业务日志
            writeLog(isLog,"执行controller方法调用结束，类名:[{}],方法名:[{}],调用参数:[{}],返回参数:[{}]耗时:[{}],", logList.toArray());
            ThreadContext.clearAll();
        }
    }

    private void writeLog(boolean isLog, String msg, Object... args) {
        if (isLog) {
            log.info(msg, args);
        }
    }

    private boolean isAopIgnore(ProceedingJoinPoint joinPoint) {
        return ServiceContainer.isAopIgnoreService(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }
}
