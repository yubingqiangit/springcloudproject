package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.common.CommonResult;
import com.yu.exception.CommonException;
import com.yu.exception.ExceptionEnums;
import com.yu.model.ModelStakeRel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public CommonResult<ModelStakeRel> getModlePost(@RequestBody com.yu.model.ModelStakeRel modelStakeRel) {
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
