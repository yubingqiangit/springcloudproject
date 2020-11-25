package com.yu.exception;

import com.yu.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/25 10:31
 */
@ControllerAdvice
public class ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value= Exception.class) //处理的异常类型
    public CommonResult myExceptionHandler(Exception e){
        logger.info("myExceptionHandler===========" + e.toString());
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;

            if(commonException.getErrorCode()==null){
                return new CommonResult(commonException.getMessage());
            }
            return new CommonResult(commonException.getErrorCode(),commonException.getMessage());
        }else{
            return CommonResult.error(-1, "系统异常~~.");
        }
    }
}
