package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;
import com.yu.config.AlipayConfig;
import com.yu.rebbitmqConfig.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付包回调
 */
@Controller
@RequestMapping("/alipay")
@EnableBinding(value = {MyProcessor.class})
public class CallBackController {
    @Autowired
    private MyProcessor myProcessor;
    private static final Logger logger = LoggerFactory.getLogger(CallBackController.class);

    @Autowired
    AlipayConfig alipayConfig;

    @RequestMapping(value = "/callback")
    @ResponseBody
    public void alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
        System.out.println("支付成功, 进入同步通知接口...");
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        logger.info("异步回调报文："+ requestParams);
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
           // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            logger.info("name:{}value:{}",name,valueStr);
            params.put(name, valueStr);
        }
        //验签时要将sign_type移除，否则验签失败
        params.remove("sign_type");
        logger.info("报文=========" + JSON.toJSONString(params));
        //验签
       // boolean signVerified = AlipaySignature.rsaCheckV2(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //璋冪敤SDK楠岃瘉绛惧悕
        boolean verify_result = AlipaySignature.rsaCheckV2(params, alipayConfig.alipay_public_key, alipayConfig.charset, "RSA2");
        logger.info("验签结果："+verify_result);
        if(verify_result) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            logger.info("验签成功out_trade_no::{}trade_no::{}total_amount::{}",out_trade_no,trade_no,total_amount);

            //发送支付完成mq消息
            logger.info("modelOutput message::{}",out_trade_no);
            Message<String> stringMessage = org.springframework.messaging.support.MessageBuilder.withPayload(out_trade_no).build();
            logger.info("modelOutput rabbitMq stringMessage::{}",stringMessage);
            boolean send = myProcessor.modelOutput().send(stringMessage);
            System.out.println("支付结果MQ send result::" + send);
        }else {
            System.out.println("验签失败......");
        }
    }

    @RequestMapping("/show")
    public String show(HttpServletRequest request, @RequestParam(value = "name", defaultValue = "springboot-thymeleaf") String name) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        logger.info("show回调页面===========" +JSON.toJSONString(parameterMap));
        request.setAttribute("name", name);
        return "index";
    }


}