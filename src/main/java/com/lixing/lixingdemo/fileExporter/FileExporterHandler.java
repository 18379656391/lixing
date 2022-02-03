package com.lixing.lixingdemo.fileExporter;

import com.alibaba.excel.util.StringUtils;
import com.lixing.lixingdemo.easyExcel.RespCustomerDailyImport;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-14 14:57
 */
@Component
@Aspect
public class FileExporterHandler {

    @Pointcut("@annotation(com.lixing.lixingdemo.fileExporter.FileExporter)")
    //@Pointcut("execution(com.lixing.lixingdemo.fileExporter.FileExporterController")
    public void handleFileExporter() {
        throw new UnsupportedOperationException();
    }

    @Around("handleFileExporter()")
    public Object fileExporterAOP(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取切点方法参数
        Object[] param = joinPoint.getArgs();
        FileExporterInputDTO inputDTO = (FileExporterInputDTO)param[0];
        // 导出文件逻辑
        if (StringUtils.isNotBlank(inputDTO.getFileName())) {
            inputDTO.setPageIndex(1);
            inputDTO.setPageSize(1);
            // 获取练级诶单的方法签名对象
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
            FileExporterOutputDTO<Serializable> out = (FileExporterOutputDTO<Serializable>) method.invoke(joinPoint.getTarget().getClass().newInstance(), inputDTO);
            int totalRecord = out.getTotal();
            inputDTO.setPageSize(totalRecord);
            // 可以丢入线程池
            FileExporterOutputDTO<Serializable> outAll = (FileExporterOutputDTO<Serializable>) method.invoke(joinPoint.getTarget().getClass().newInstance(), inputDTO);
        }
        // 正常查询逻辑
        FileExporterOutputDTO<?> out = (FileExporterOutputDTO<?>)joinPoint.proceed();
        return out;
    }

}
