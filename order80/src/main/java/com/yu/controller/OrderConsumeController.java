package com.yu.controller;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yu.common.CommonResult;
import com.yu.feign.FeignService;
import com.yu.model.ModelStakeRel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

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


