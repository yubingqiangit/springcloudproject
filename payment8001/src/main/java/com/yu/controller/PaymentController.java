package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.common.CommonResult;
import com.yu.entity.ModelStakeRel;
import com.yu.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @RequestMapping("/get/{id}")
    public CommonResult<ModelStakeRel> getCount(@PathVariable("id") Integer id) {
        System.out.println(String.format("Server Port::[<%s>]",serverPort));
        if (id==222) {
            return new CommonResult<>(400, String.format("request serverPort:[<%s>] get count error.",serverPort));
        }
        ModelStakeRel modelStakeRel = new ModelStakeRel();
        modelStakeRel.setModelId("34343434");
        modelStakeRel.setStakeNo("2222222222222");
        modelStakeRel.setRelTime(new Date());
        return  new CommonResult(modelStakeRel);
    }

    @Autowired
    private ModelService modelService;

    @GetMapping("/mybatis/model/{id}")
    public CommonResult<ModelStakeRel> getModelById(@PathVariable("id")Integer id) {
        System.out.println("request para id = " + id);
        ModelStakeRel model = modelService.getModelStakeRelById(id);
        logger.info("ModelStake查询结果::" + JSON.toJSONString(model));
        if (model == null) {
            return new CommonResult<>(444, String.format("server port::[<%s>]model is not found error.",serverPort));
        }
        System.out.println(model.toString());
        return new CommonResult<>(model);
    }

    @RequestMapping(value = "/mybatis/{id}/{modelId}",method = RequestMethod.GET)
    public CommonResult<ModelStakeRel> getModelByParams( @PathVariable("id")Integer id,@PathVariable("modelId")String modelId) {
        logger.info("======id{},modelId{}",id,modelId);
        ModelStakeRel modelStakeRel = modelService.getModelByParams(id, modelId);
        logger.info("model params result{}",JSON.toJSON(modelStakeRel));
        if (modelStakeRel == null)
            return new CommonResult<>(444, String.format("server port::[<%s>]model params  do not found exception.",serverPort));
        return new CommonResult<>(modelStakeRel);
    }
}
