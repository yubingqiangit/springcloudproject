package com.yu.controller;/**
 *
 * @author yubingqian
 * @date 2021-01-25 16:47
 *
 */

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
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

    @NacosValue(value = "${infor:bbbbb}", autoRefreshed = true)
    private String info;

    @GetMapping(value = "config")
    public String config() {
        System.out.println("获取rocketmq_path" + info
        );
        return info;
    }


    @SentinelResource(value = "hello", blockHandler = "blockHandlerHello")
    @GetMapping("/test")
    public String test(String name) {
        System.out.println("前段请求");
        String demo = demoFeignService.demo(name);
        System.out.println("接口返回参数：" + demo);
        return demoFeignService.demo(name);
    }

    public String blockHandlerHello(BlockException e) {
        return "限流了";
    }
}
