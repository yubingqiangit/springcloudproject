package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.payservice.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/25 11:01 
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    AlipayService alipayService;



    @RequestMapping("/pay")
    public void payMent(HttpServletResponse response, HttpServletRequest request) {
        try {
            alipayService.aliPay(response,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
