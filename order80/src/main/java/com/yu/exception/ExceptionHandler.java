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
        logger.info("ExceptionHandler>>>>>>>errorMessage::{}",e.getMessage().toString());
        //自定义异常
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            if(commonException.getErrorCode()==null)
                return new CommonResult(-1, commonException.getMessage());
            return new CommonResult(commonException.getErrorCode(),commonException.getMessage());
        }
        //未知异常
        return CommonResult.error(-1, "系统异常~~.");

    }
}
