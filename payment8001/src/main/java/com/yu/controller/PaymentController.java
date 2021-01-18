package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.common.CommonResult;
import com.yu.entity.ModelStakeRel;
import com.yu.exception.CommonException;
import com.yu.exception.ExceptionEnums;
import com.yu.mybatis.entity.PayPay;
import com.yu.mybatis.service.ModelStakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
    private ModelStakeService modelStakeService;

    @GetMapping("/mybatis/model/{id}")
    public CommonResult<ModelStakeRel> getModelById(@PathVariable("id")Integer id) {
        System.out.println("request para id = " + id);
        ModelStakeRel model = new ModelStakeRel();
        com.yu.mybatis.entity.ModelStakeRel modelStakeRel = modelStakeService.selectById(id);
        BeanUtils.copyProperties(modelStakeRel,model);
        logger.info("ModelStake查询结果::" + JSON.toJSONString(model));
        if (model == null) {
            return new CommonResult<>(444, String.format("server port::[<%s>]model is not found error.",serverPort));
        }
        System.out.println(model.toString());
        return new CommonResult<>(model);
    }

    /**
     * feign构造多参get请求controller
     * @param id
     * @param modelId
     * @return
     */
    @RequestMapping(value = "/mybatis/{id}/{modelId}",method = RequestMethod.GET)
    public CommonResult<ModelStakeRel> getModelByParams( @PathVariable("id")Integer id,@PathVariable("modelId")String modelId) {
        logger.info("======id{},modelId{}",id,modelId);
        ModelStakeRel modelStakeRel = new ModelStakeRel();
        QueryWrapper<com.yu.mybatis.entity.ModelStakeRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("model_id", modelId);
        com.yu.mybatis.entity.ModelStakeRel modelStakeRel1 = modelStakeService.selectOne(queryWrapper);
        logger.info("=======ModelStakeRel1" + JSON.toJSONString(modelStakeRel1));
        BeanUtils.copyProperties(modelStakeRel1,modelStakeRel);
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
    public CommonResult<ModelStakeRel> getModlePost(@RequestBody com.yu.model.ModelStakeRel modelStakeRel) {
        logger.info("method getModelPost modelStakeRel:{}", JSON.toJSON(modelStakeRel));
        ModelStakeRel modelByParams = new ModelStakeRel();
        QueryWrapper<com.yu.mybatis.entity.ModelStakeRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", modelStakeRel.getId());
        queryWrapper.eq("model_id", modelStakeRel.getModelId());
        com.yu.mybatis.entity.ModelStakeRel modelStakeRel1 = modelStakeService.selectOne(queryWrapper);
        logger.info("=======ModelStakeRel1" + JSON.toJSONString(modelStakeRel1));
        BeanUtils.copyProperties(modelStakeRel1,modelByParams);
        logger.info("model params result::{}",JSON.toJSON(modelByParams));
        if (modelStakeRel == null)
            /*return new CommonResult<>(444, String.format("server port::[<%s>]model params  do not found exception.",serverPort));*/
            throw new CommonException(ExceptionEnums.valueOf("MODEL_NOT_FOUND"));
        return new CommonResult<>(modelByParams);
    }

    /**
     * feign构造多参post请求列表
     * @param modelStakeRel
     * @return
     */
    @RequestMapping(value = "/post/list",method = RequestMethod.POST)
    public CommonResult<List<ModelStakeRel>> getModelPostList(@RequestBody com.yu.entity.ModelStakeRel modelStakeRel) {
        logger.info("method getModelPost modelStakeRel:{}", JSON.toJSON(modelStakeRel));
        List<com.yu.entity.ModelStakeRel> modelPostList = new ArrayList<>();
        QueryWrapper<com.yu.mybatis.entity.ModelStakeRel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", modelStakeRel.getId());
        queryWrapper.eq("model_id", modelStakeRel.getModelId());
        List<com.yu.mybatis.entity.ModelStakeRel> modelStakeRels = modelStakeService.selectList(queryWrapper);
        modelStakeRels.stream().forEach(x->{
            logger.info("modelStakeRels:{}",JSON.toJSONString(x));
        });
        BeanUtils.copyProperties(modelStakeRels,modelPostList);
        return new CommonResult<List<com.yu.entity.ModelStakeRel>>(modelPostList);
    }
}
