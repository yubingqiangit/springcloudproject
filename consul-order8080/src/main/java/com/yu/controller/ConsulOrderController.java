package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.common.CommonResult;
import com.yu.feign.FeignService;
import com.yu.model.ModelStakeRel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/24 17:27 
 */
@RestController
public class ConsulOrderController {
    private Logger logger = LoggerFactory.getLogger(ConsulOrderController.class);

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private FeignService feignService;

    @GetMapping("/consul/order/{id}")
    public CommonResult consulOrder(@PathVariable("id")Integer id) {
        ResponseEntity<CommonResult> responseEntity = restTemplate.
                getForEntity("http://consul-provider-peyment/consul/request/" + id, CommonResult.class);
        System.out.println(responseEntity.toString());
        if (!responseEntity.getStatusCode().is2xxSuccessful())
            return new CommonResult<>(444, "consul order error.");
        return responseEntity.getBody();
    }


    @RequestMapping(value = "/post/model",method = RequestMethod.POST)
    public CommonResult<ModelStakeRel> getModlePost(@RequestBody ModelStakeRel modelStakeRel) {
        logger.info("consul method getModelPost ModelStaleRel:{}", JSON.toJSON(modelStakeRel));
        CommonResult<ModelStakeRel> model = feignService.getModlePost(modelStakeRel);
        logger.info("response model::{}", JSON.toJSON(model));
        return model;
    }


}
