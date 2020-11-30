package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.common.CommonResult;
import com.yu.feign.FeignConfigruationService;
import com.yu.model.ModelStakeRel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 自定义feignConfigruation请求
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/28 15:00 
 */
@RestController
public class MyFeignConfigruationController {
    private Logger logger = LoggerFactory.getLogger(MyFeignConfigruationController.class);
    @Autowired
    private FeignConfigruationService feignConfigruationService;
    /**
     * feign构造多参post请求列表
     * @param modelStakeRel
     * @return
     */
    @RequestMapping(value = "/feign/config",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ModelStakeRel>> feingConfigruation(@RequestBody ModelStakeRel modelStakeRel) {
        logger.info("method feingConfigruation modelStakeRel::{}", JSON.toJSONString(modelStakeRel));
        CommonResult<List<ModelStakeRel>> modelPostList = feignConfigruationService.getModelPostList(modelStakeRel);
        return modelPostList;
    }
}
