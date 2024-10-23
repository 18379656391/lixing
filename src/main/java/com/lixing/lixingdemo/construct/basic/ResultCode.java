package com.lixing.lixingdemo.construct.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/17
 */
@Getter
public enum ResultCode implements BasicResultCode{
    SUCCESS("200", "成功"),
    FAIL("500", "失败"),
    PARAM_ERROR("400", "参数错误"),
    FORBIDDEN("401", "无权限"),
    NOT_LOGIN("403", "净值访问"),
    NOT_FOUND("404", "未找到"),
    REQUEST_TIMEOUT("503", "服务不可用"),

    SYSTEM_EXCEPTION("-1", "系统异常"),
    DATE_EXPIRED("1101", "日期已经过期！");

    private final String code;
    private final String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public String getResultCode() {
        return getCode();
    }

    @Override
    public String getResultMsg() {
        return getMsg();
    }
}
