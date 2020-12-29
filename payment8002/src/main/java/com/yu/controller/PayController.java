package com.yu.controller;/**
 *
 * @author yubingqian
 * @date 2020-12-28 8:58
 *
 */

import com.alibaba.fastjson.JSON;
import com.yu.common.CommonResult;
import com.yu.model.PayReqItem;
import com.yu.model.PayRespItem;
import com.yu.service.AlipayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/28 8:58 
 */
@RestController
@RequestMapping("/payment")
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    AlipayService alipayService;


    /**
     * post请求
     * @param payReqItem
     * @return
     */

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public CommonResult<PayRespItem> payMent(@RequestBody PayReqItem payReqItem) {
        try {
            logger.info("收到支付请求:{}", JSON.toJSONString(payReqItem));
            String result = alipayService.aliPay(payReqItem);
            logger.info("支付宝返回form标签:{}",result);
            PayRespItem payRespItem = new PayRespItem();
            payRespItem.setOrderNo(payReqItem.getOrderNo());
            payRespItem.setStatus("success");
            payRespItem.setResult(result);
            return new CommonResult<>(payRespItem);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    @RequestMapping(value = "/payget/{orderNo}/{amount}",method = RequestMethod.GET)
    public CommonResult<PayRespItem> payGet(@PathVariable("orderNo") String orderNo, @PathVariable("amount")String amount) {
        try {
            logger.info("method pay orderNo:{}amount:{}", orderNo,amount);
             alipayService.aliPay(orderNo,amount);
            PayRespItem payRespItem = new PayRespItem();
            payRespItem.setOrderNo(orderNo);
            payRespItem.setStatus("call pay success....");
            return new CommonResult<>(payRespItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
