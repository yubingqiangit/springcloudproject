package com.yu.controller;/**
 *
 * @author yubingqian
 * @date 2021-01-25 16:47
 *
 */

import com.yu.feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/25 16:47 
 */
@RestController
public class DemoController {

    @Autowired
    private Feign demoFeignService;

    @GetMapping("/test")
    public String test(String name) {
        System.out.println("前段请求");
        String demo = demoFeignService.demo(name);
        System.out.println("接口返回参数：" + demo);
        return demoFeignService.demo(name);
    }
}
