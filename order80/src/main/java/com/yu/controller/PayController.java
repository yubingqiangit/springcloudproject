package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.common.CommonResult;
import com.yu.feign.FeignService;
import com.yu.model.ModelStakeRel;
import com.yu.model.PayReqItem;
import com.yu.model.PayRespItem;
import com.yu.utils.OrderNoRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/28 9:05 
 */
@RestController
public class PayController {
    private Logger logger = LoggerFactory.getLogger(PayController.class);
    @Resource
    private FeignService feignService;

    @RequestMapping(value = "/payment/center/pay")
    public String pay(HttpServletResponse response, HttpServletRequest request) {
        String no = OrderNoRandom.getNo();
        logger.info("method pay orderNo:{}amount:{}", no,request.getParameter("amount"));
        PayReqItem payReqItem = new PayReqItem();
        payReqItem.setAmount(request.getParameter("amount"));
        payReqItem.setOrderNo(no);
        logger.info("请求支付中心参数payItem:{}",JSON.toJSONString(payReqItem));
        CommonResult<PayRespItem> payRespItemCommonResult = feignService.pay(payReqItem);
        logger.info("response model::{}", JSON.toJSON(payRespItemCommonResult));
        return payRespItemCommonResult.getData().getResult();
    }
}
