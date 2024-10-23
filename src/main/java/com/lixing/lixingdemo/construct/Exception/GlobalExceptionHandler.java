package com.lixing.lixingdemo.construct.Exception;

import com.lixing.lixingdemo.construct.basic.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/21
 * @ControllerAdvice注解的作用
 * 全局异常处理：可以用来定义全局的异常处理器，捕获所有控制器中抛出的异常，并进行统一处理。
 * 数据绑定：可以用来定义全局的数据绑定逻辑，例如自定义的 @InitBinder 方法。
 * 表单验证：可以用来定义全局的表单验证逻辑，例如自定义的 @ModelAttribute 方法。
 */
@RestControllerAdvice(basePackages = {"com.lixing.lixingdemo.controller"})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseResult BusinessExceptionHandler(BusinessException e) {
        log.error("业务异常---------：", e);
        return ResponseResult.fail(e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * MethodArgumentNotValidException-Spring封装的参数验证异常处理
     * <p>MethodArgumentNotValidException：作用于 @Validated @Valid 注解，接收参数加上@RequestBody注解（json格式）才会有这种异常。</p>
     *
     * @param e MethodArgumentNotValidException异常信息
     * @return 响应数据
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors()
                .stream()
                .map(n -> String.format("%s: %s", n.getField(), n.getDefaultMessage()))
                .reduce((x, y) -> String.format("%s; %s", x, y))
                .orElse("参数输入有误");
        log.error("【ExceptionControllerAdvice】Param check MethodArgumentNotValidException，参数校验异常：{} \n", msg, e);
        return ResponseResult.fail(msg);
    }

    /**
     * ConstraintViolationException-jsr规范中的验证异常，嵌套检验问题
     * <p>ConstraintViolationException：作用于 @NotBlank @NotNull @NotEmpty 注解，校验单个String、Integer、Collection等参数异常处理。</p>
     * <p>注：Controller类上必须添加@Validated注解，否则接口单个参数校验无效</p>
     *
     * @param e ConstraintViolationException异常信息
     * @return 响应数据
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        String msg = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        log.error("【ExceptionControllerAdvice】Param check ConstraintViolationException，参数校验异常：{} \n", msg, e);
        return ResponseResult.fail(msg);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseResult IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("空指针异常:", e);
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult ExceptionHandler(Exception e) {
        log.error("系统异常:", e);
        return ResponseResult.fail(e.getMessage());
    }

}
