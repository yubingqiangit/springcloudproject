package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.yu.config.AlipayConfig;
import com.yu.utils.AliPayClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 首页
 */
@Controller
@RequestMapping("/alipay")
public class CallBackController {

    private static final Logger logger = LoggerFactory.getLogger(CallBackController.class);

    @Autowired
    AlipayConfig alipayConfig;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request, @RequestParam(value = "name", defaultValue = "springboot-thymeleaf") String name) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        logger.info("hello回调页面===========" +JSON.toJSONString(parameterMap));
        request.setAttribute("name", name);
        return "hello";
    }
}