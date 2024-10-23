package com.lixing.lixingdemo.construct.Exception;

import com.lixing.lixingdemo.construct.basic.BasicResultCode;
import com.lixing.lixingdemo.construct.basic.ResultCode;
import lombok.Getter;

import java.util.Objects;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/19
 * extends RuntimeException 不受检查异常
 * extends Exception 受检查异常
 */
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 3890725034019825841L;


    protected String errorCode;

    protected String errorMessage;

    public BusinessException(String errorCodeOrMsg) {
        super( ErrorCodeContainer.ERROR_INFO_CONTAINER.containsKey(errorCodeOrMsg)?
                "[" + errorCodeOrMsg + "]-[" + ErrorCodeContainer.getErrorMessage(errorCodeOrMsg) + "]" :
                "[" + ResultCode.SYSTEM_EXCEPTION.getResultCode() + "]-[" + errorCodeOrMsg + "]");
        boolean isCode = ErrorCodeContainer.ERROR_INFO_CONTAINER.containsKey(errorCodeOrMsg);
        if (isCode) {
            this.errorCode = errorCodeOrMsg;
            this.errorMessage = ErrorCodeContainer.getErrorMessage(errorCodeOrMsg);
        }else {
            this.errorCode = ResultCode.SYSTEM_EXCEPTION.getResultCode();
            this.errorMessage = errorCodeOrMsg;
        }
    }

    public BusinessException(String errorCode, String errorMessage) {
        // 将异常信息传给父类，后续可以用过 getMessage()方法获取
        super("[" + errorCode + "]-[" + errorMessage+ "]");
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorCode, String errorMessage, Throwable throwable) {
        // 将异常信息传给父类，后续可以用过 getMessage()方法获取
        super("[" + errorCode + "]-[" + errorMessage + "]", throwable);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorCode, Object... params) {
        super("[" + errorCode + "]-[" + String.format(ErrorCodeContainer.getErrorMessage(errorCode), params)+ "]");
        this.errorCode = errorCode;
        this.errorMessage = String.format(ErrorCodeContainer.getErrorMessage(errorCode), params);
    }

    public BusinessException(BasicResultCode resultCodeEnum) {
        super("[" + resultCodeEnum.getResultCode() + "]-[" + resultCodeEnum.getResultMsg()+ "]");
        this.errorCode = resultCodeEnum.getResultCode();
        this.errorMessage = resultCodeEnum.getResultMsg();
    }

    public BusinessException(BasicResultCode resultCodeEnum, Throwable throwable) {
        super("[" + resultCodeEnum.getResultCode() + "]-[" + resultCodeEnum.getResultMsg()+ "]", throwable);
        this.errorCode = resultCodeEnum.getResultCode();
        this.errorMessage = resultCodeEnum.getResultMsg();
    }

    /**
     * 把当前线程堆栈帧的信息记录到Throwable对象中
     * 直接return,避免对业务异常进行昂贵且无用的堆栈跟踪，规避不必要的性能浪费
     * @return
     */
    public synchronized Throwable fillInStackTrace() {
        return this;
    }


}
