package com.yu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/25 16:54 
 */
@RestController
public class DemoController {


    @Value("${server.port}")
    private Integer port;
    @GetMapping("/demo")
    public String demo(String name) {
        System.out.println("收到请求");
        return "hello " + name + port;
    }
}
