package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.common.CommonResult;
import com.yu.model.ModelStakeRel;
import com.yu.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    public CommonResult<com.yu.entity.ModelStakeRel> getModelById(@PathVariable("id")Integer id) {
        System.out.println("request para id = " + id);
        com.yu.entity.ModelStakeRel model = modelService.getModelStakeRelById(id);
        logger.info("ModelStake查询结果::" + JSON.toJSONString(model));
        if (model == null) {
            return new CommonResult<>(444, String.format("server port::[<%s>]model is not found error.",serverPort));
        }
        return new CommonResult<>(model);
    }

    /**
     * feign构造多参get请求controller
     * @param id
     * @param modelId
     * @return
     */
    @RequestMapping(value = "/mybatis/{id}/{modelId}",method = RequestMethod.GET)
    public CommonResult<com.yu.entity.ModelStakeRel> getModelByParams(@PathVariable("id")Integer id, @PathVariable("modelId")String modelId) {
        logger.info("======id{},modelId{}",id,modelId);
        com.yu.entity.ModelStakeRel modelStakeRel = modelService.getModelByParams(id, modelId);
        logger.info("model params result{}",JSON.toJSON(modelStakeRel));
        if (modelStakeRel == null)
            return new CommonResult<>(444, String.format("server port::[<%s>]model params  do not found exception.",serverPort));
        return new CommonResult<>(modelStakeRel);
    }

    /**
     * fengin构造多参post请求controller
     * @param modelStakeRel
     * @return
     */
    @RequestMapping(value = "/post/model",method = RequestMethod.POST)
    public CommonResult<com.yu.entity.ModelStakeRel> getModlePost(@RequestBody com.yu.model.ModelStakeRel modelStakeRel) {
        logger.info("method getModelPost modelStakeRel:{}", JSON.toJSON(modelStakeRel));
        com.yu.entity.ModelStakeRel modelByParams = modelService.getModelByParams(modelStakeRel.getId(), modelStakeRel.getModelId());
        logger.info("model params result{}",JSON.toJSON(modelByParams));
        return new CommonResult<>(modelByParams);
    }

    /**
     * feign构造多参post请求列表
     * @param modelStakeRel
     * @return
     */
    @RequestMapping(value = "/post/list",method = RequestMethod.POST)
    public CommonResult<List<com.yu.entity.ModelStakeRel>> getModelPostList(@RequestBody com.yu.entity.ModelStakeRel modelStakeRel) {
        logger.info("method getModelPost modelStakeRel:{}", JSON.toJSON(modelStakeRel));
        List<com.yu.entity.ModelStakeRel> modelPostList = modelService.getModelPostList(modelStakeRel);
        modelPostList.stream().forEach(x->{
            logger.info("modelPostList:{}",JSON.toJSONString(x));
        });
        return new CommonResult<List<com.yu.entity.ModelStakeRel>>(modelPostList);
    }
}
