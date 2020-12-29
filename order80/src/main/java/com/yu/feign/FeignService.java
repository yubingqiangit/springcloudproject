package com.yu.feign;

import com.yu.common.CommonResult;
import com.yu.fallBack.FeignHystrixFallBackImpl;
import com.yu.model.ModelStakeRel;
import com.yu.model.PayReqItem;
import com.yu.model.PayRespItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * feign调用接口定义
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/23 10:18 
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback = FeignHystrixFallBackImpl.class)
public interface FeignService {
    @GetMapping("/get")
    public CommonResult<ModelStakeRel> getCount();

    @GetMapping("/mybatis/model/{id}")
    public CommonResult<ModelStakeRel> getForMybatis(@PathVariable("id") Integer id);

    /**
     * feign构造多参get请求-每个参数对应一个@PathVariable
     * @param id
     * @param modelId
     * @return
     */
    @RequestMapping(value = "/mybatis/{id}/{modelId}",method = RequestMethod.GET)
    public CommonResult<ModelStakeRel> getModelByParams(@PathVariable("id") Integer id,@PathVariable("modelId")String modelId);


    /**
     * feign构造多参post请求
     * @param modelStakeRel
     * @return
     */
    @RequestMapping(value = "/post/model")
    public CommonResult<ModelStakeRel> getModlePost(@RequestBody ModelStakeRel modelStakeRel);


    /**
     * 请求列表
     * @param modelStakeRel
     * @return
     */
    @RequestMapping(value = "/post/list")
    public CommonResult<List<ModelStakeRel>> getModelPostList(@RequestBody ModelStakeRel modelStakeRel);

    /**
     * 支付
     * @param payItem
     * @return
     */
    @RequestMapping(value = "/payment/pay")
    public CommonResult<PayRespItem> pay(@RequestBody PayReqItem payItem);


    /**
     * 支付
     * @param orderNo
     * @param amount
     * @return
     */
    @RequestMapping(value = "/payment/payget/{orderNo}/{amount}",method = RequestMethod.GET)
    public CommonResult<PayRespItem> payGet(@PathVariable("orderNo") String orderNo,@PathVariable("amount")String amount);



}
