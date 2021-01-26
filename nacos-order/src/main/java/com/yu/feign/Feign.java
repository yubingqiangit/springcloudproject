package com.yu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/25 16:47 
 */
@FeignClient("nacos-discovery-provider")
public interface Feign {
    @GetMapping("/demo")
    String demo(@RequestParam("name") String name);
}
