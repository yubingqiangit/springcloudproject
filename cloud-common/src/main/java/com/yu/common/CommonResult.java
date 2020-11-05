package com.yu.common;

import java.io.Serializable;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/23 16:44 
 */
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public CommonResult() {

    }

    public CommonResult( T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }
    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
