package com.yu.controller;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yu.common.CommonResult;
import com.yu.common.RedisUtil;
import com.yu.exception.ExceptionEnums;
import com.yu.exception.CommonException;
import com.yu.feign.CommonFeignClient;
import com.yu.feign.FeignService;
import com.yu.model.ModelStakeRel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.util.List;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/22 16:21 
 */
@RestController
public class OrderConsumeController {
    public static final String PAYMENT_URL ="http://localhost:8001";

    private Logger logger = LoggerFactory.getLogger(OrderConsumeController.class);

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private FeignService feignService;

    @Resource
    private CommonFeignClient commonFeignClient;

    @Value("${lock.key}")
    private String lockKey;


    @GetMapping("/test/test/{id}")
    public String test(@PathVariable("id") Integer id){
        if (id == 1)
            return new CommonResult<ModelStakeRel>(-1, "error.").toString();
        else
            return new CommonResult<ModelStakeRel>().toString();
    }

    @GetMapping("/order/object/{id}")
    public String getForObject(@PathVariable("id") Integer id) {
        CommonResult commonResult = restTemplate.
                getForObject("http://CLOUD-PAYMENT-SERVICE/get/" + id, CommonResult.class);
        System.out.println("payment service commonreult code " + commonResult.getCode());
        if (commonResult.getCode() != 200) {
            return "get for object error code" + commonResult.getCode();
        }
        //CommonResult<ModelStakeRel> commonResult = feignService.getCount();
        //ModelStakeRel modelStakeRel = commonResult.getData();
        // System.out.println(modelStakeRel.toString());
        return  commonResult.getData().toString();
    }

    @GetMapping("/payment/entity/{id}")
    public CommonResult<ModelStakeRel> getForEntity(@PathVariable("id") Integer id) {
        System.out.println("id===" + id);
        ResponseEntity<CommonResult> responseEntity = restTemplate.
                getForEntity("http://CLOUD-PAYMENT-SERVICE/get/" + id, CommonResult.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful())
            return responseEntity.getBody();
        CommonResult commonResult = responseEntity.getBody();
        System.out.println(String.format("CommonResult code::[<%s>]",commonResult.getCode()));
        return commonResult;
    }

    @GetMapping("/payment/mybatis/{id}")
    public CommonResult<ModelStakeRel> getForMybatis(@PathVariable("id") Integer id) {
        logger.info("get for mybatis request param id::" + id);
        ResponseEntity<CommonResult> responseEntity = restTemplate.
                getForEntity("http://CLOUD-PAYMENT-SERVICE/mybatis/model/" + id, CommonResult.class);
        logger.info("接口返回：：" + JSON.toJSONString(responseEntity));
        if (!responseEntity.getStatusCode().is2xxSuccessful())
            return responseEntity.getBody();
        CommonResult commonResult = responseEntity.getBody();
        System.out.println(JSON.toJSON(commonResult));
        System.out.println(String.format("CommonResult code::[<%s>]",commonResult.getCode()));
        return commonResult;
    }

    @GetMapping("/get/openfeign/{id}")
    public CommonResult<ModelStakeRel> getForOpenFeign(@PathVariable("id") Integer id) {
        CommonResult<ModelStakeRel> commonResult = feignService.getForMybatis(id);
        logger.info("openfeign接口返回::" + JSON.toJSONString(commonResult));
        return commonResult;
    }

    /**
     * feign构造多参get请求每个参数对应一个@PathVariable
     * @param modelStakeRel
     * @return
     */
    @GetMapping("/get/modelparams")   //http://localhost/get/modelparams?id=1&modelId=343434343
    public CommonResult<ModelStakeRel> getModelParams(ModelStakeRel modelStakeRel){
        logger.info("ModelStakeRel======"+ JSON.toJSONString(modelStakeRel));
        logger.info("redis lock key:{}", lockKey.concat(modelStakeRel.getModelId()));
        String setNX = RedisUtil.getRedisUtil().setNX(lockKey.concat(modelStakeRel.getModelId()), modelStakeRel.getModelId(), 50);
        if ("error".equals(setNX))
            return new CommonResult<>(600, String.format("重复请求，modelId::[<%s>]",modelStakeRel.getModelId()));
        CommonResult<ModelStakeRel> model = feignService.getModelByParams(modelStakeRel.getId(), modelStakeRel.getModelId());
        logger.info("get model by more params result{}", model);
        return model;
    }

    /**
     * feign构造多参post请求
     * @param modelStakeRel
     * @return
     */
    @RequestMapping(value = "/post/model",method = RequestMethod.POST)
    public CommonResult<ModelStakeRel> getModlePost(@RequestBody ModelStakeRel modelStakeRel) {
        logger.info("method getModelPost ModelStaleRel:{}", JSON.toJSON(modelStakeRel));
        CommonResult<ModelStakeRel> model = feignService.getModlePost(modelStakeRel);
        logger.info("response model::{}", JSON.toJSON(model));
        return model;
    }

    /**
     * feign构造多参post请求列表
     * @param modelStakeRel
     * @return
     */
    @RequestMapping(value = "/post/list",method = RequestMethod.POST)
    public CommonResult<List<ModelStakeRel>> getModelPostList(@RequestBody ModelStakeRel modelStakeRel) {
        CommonResult<List<ModelStakeRel>> modelPostList = feignService.getModelPostList(modelStakeRel);
        return modelPostList;
    }

    /**
     * feign集成示例
     * @param id
     * @return
     */
    @RequestMapping(value = "/common/model/{id}",method = RequestMethod.GET)
    public CommonResult<ModelStakeRel> getModelPostList(@PathVariable("id") Integer id) {
        CommonResult<ModelStakeRel> modelStakeRelCommonResult = commonFeignClient.commonResult(id);
        return modelStakeRelCommonResult;
    }


    /**
     * 基于restTempleate|openfeign回调函数配置hystrix方式(一)
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getDefultModel")
    @GetMapping("get/hystrix/{id}")
    public CommonResult<ModelStakeRel> getForHystrix(@PathVariable("id") Integer id) {
        return feignService.getForMybatis(id);
    }

    /**
     * 错误回调函数
     * @param id
     * @return
     */
    public CommonResult<ModelStakeRel> getDefultModel(Integer id){
        System.out.println("******************getDefultModel*************************");
        return new CommonResult(500, String.format("id::[<%s>] get openfeign hystrix error",id));
    }

    /**
     * openfeign基于注解配置hystrix方式(二)
     * @param id
     * @return
     */
    @GetMapping("/hystrix/config/{id}")
    public CommonResult<ModelStakeRel> openfeignHystrix(@PathVariable("id") Integer id) {
        System.out.println("openfeignHystrix======" + id);
        return feignService.getForMybatis(id);
    }

}


