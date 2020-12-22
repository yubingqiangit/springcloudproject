package com.yu.exception;

/**
 *
 * @author yubingqian
 * @date 2020-11-25 9:39
 *
 */
public enum ExceptionEnums {

MODEL_NOT_FOUND("model not found error.",2001),
MODEL_LIST_IS_EMPTY("model list is empty error.",2002),
REQUEST_REUSE("重复请求.",600);



    private String message;
    private Integer errorCode;

    ExceptionEnums(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
