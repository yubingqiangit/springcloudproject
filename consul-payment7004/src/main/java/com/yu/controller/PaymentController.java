package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.common.CommonResult;
import com.yu.exception.CommonException;
import com.yu.exception.ExceptionEnums;
import com.yu.model.ModelStakeRel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/22 15:58 
 */
@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Value("${server.port}")
    private String serverPort;



    /**
     * fengin构造多参post请求controller
     * @param modelStakeRel
     * @return
     */
    @RequestMapping(value = "/post/model",method = RequestMethod.POST)
    public CommonResult<ModelStakeRel> getModlePost(@RequestBody ModelStakeRel modelStakeRel) {
        logger.info("method getModelPost modelStakeRel:{}", JSON.toJSON(modelStakeRel));
        ModelStakeRel modelByParams = new ModelStakeRel();
        modelByParams.setModelId("99999999999999999");
        modelByParams.setStakeNo("11111111111111111");
        logger.info("model params result::{}",JSON.toJSON(modelByParams));
        if (modelByParams == null)
            /*return new CommonResult<>(444, String.format("server port::[<%s>]model params  do not found exception.",serverPort));*/
            throw new CommonException(ExceptionEnums.valueOf("MODEL_NOT_FOUND"));
        return new CommonResult<>(modelByParams);
    }

}
