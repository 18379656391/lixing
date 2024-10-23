package com.lixing.lixingdemo.construct.basic;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/17
 */
@Data
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -4399744526566979874L;

    private String returnCode;

    private String returnMsg;

    private String traceId;

    private T data;
    public ResponseResult(BasicResultCode code) {
        this(code.getResultCode(), code.getResultMsg(), null);
    }

    public ResponseResult(String returnCode, String returnMsg) {
        this(returnCode, returnMsg, null);
    }
    public ResponseResult(BasicResultCode code, T data) {
        this(code.getResultCode(), code.getResultMsg(), data);
    }
    public ResponseResult(String returnCode, String returnMsg, T data) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.data = data;
    }

    public Boolean isSuccess() {
        return ResultCode.SUCCESS.getCode().equals(returnCode);
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResultCode.SUCCESS, data);
    }
    public static <T> ResponseResult<T> success(T data, String msg) {
        return new ResponseResult<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResponseResult<T> fail(String msg) {
        return new ResponseResult<>(ResultCode.FAIL.getCode(), msg);
    }

    public static <T> ResponseResult<T> fail(String returnCode, String returnMsg) {
        return new ResponseResult<>(returnCode, returnMsg);
    }

    public static <T> ResponseResult<T> fail(BasicResultCode codeEnum) {
        return new ResponseResult<>(codeEnum.getResultCode(), codeEnum.getResultMsg());
    }

    public static <T> ResponseResult<T> fail(BasicResultCode codeEnum, Object... msg) {
        String returnMsg = String.format(codeEnum.getResultMsg(), msg);
        return new ResponseResult<>(codeEnum.getResultCode(), returnMsg);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
