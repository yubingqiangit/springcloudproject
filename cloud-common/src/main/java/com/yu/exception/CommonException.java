package com.yu.exception;
/**
 * 全局异常
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/25 9:43 
 */
public class CommonException extends RuntimeException {
    private Integer errorCode;
    private String message;

    public CommonException(ExceptionEnums exceptionEnums) {
        super(exceptionEnums.getMessage());
        this.errorCode = exceptionEnums.getErrorCode();
        this.message = exceptionEnums.getMessage();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
