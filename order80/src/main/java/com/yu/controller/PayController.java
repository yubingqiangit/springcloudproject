package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.JsApiBaseDTO;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.yu.common.CommonResult;
import com.yu.config.AlipayConfig;
import com.yu.config.SyncConfig;
import com.yu.config.sync.Sync;
import com.yu.feign.FeignService;
import com.yu.model.ModelStakeRel;
import com.yu.model.PayReqItem;
import com.yu.model.PayRespItem;
import com.yu.utils.OrderNoRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 支付入口
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/28 9:05 
 */
@RestController
public class PayController {
    private Logger logger = LoggerFactory.getLogger(PayController.class);
    @Resource
    private FeignService feignService;




    /**
     * 模拟订单中心通过feign调支付中心实现支付预下单
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/payment/center/pay")
    public String pay(HttpServletResponse response, HttpServletRequest request) throws ExecutionException, InterruptedException {
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


    /**
     * 生成app支付获取签名串
     * @return
     */
    @RequestMapping(value = "/app/getStr")
    public String getAppStr(HttpServletResponse response, HttpServletRequest request) {
        String amount = request.getParameter("amount");
        String no = OrderNoRandom.getNo();
        logger.info("getAppStr 支付金额：{}流水号：{}", amount,no);
        PayReqItem payReqItem = new PayReqItem();
        payReqItem.setAmount(amount);
        payReqItem.setOrderNo(no);
        CommonResult<String> stringCommonResult = feignService.qrStr(payReqItem);
        logger.info("stringCommonResult===" + JSON.toJSONString(stringCommonResult));
        logger.info("获取二维码字符串：{}", stringCommonResult.getMessage());
        return stringCommonResult.getMessage();
    }


    @RequestMapping(value = "/create/qrCode")
    public String createQrCode(HttpServletResponse response, HttpServletRequest request) {
        String amount = request.getParameter("amount");
        String no = OrderNoRandom.getNo();
        logger.info("createQrCode 支付金额：{}流水号：{}", amount,no);
        PayReqItem payReqItem = new PayReqItem();
        payReqItem.setAmount(amount);
        payReqItem.setOrderNo(no);
        CommonResult<String> stringCommonResult = feignService.createQrCode(payReqItem);
        logger.info("stringCommonResult===" + JSON.toJSONString(stringCommonResult));
        logger.info("生成二维码结果：{}", stringCommonResult.getMessage());
        return stringCommonResult.getMessage();
    }
}
