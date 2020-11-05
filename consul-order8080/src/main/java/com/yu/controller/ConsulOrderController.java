package com.yu.controller;

import com.yu.common.CommonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/24 17:27 
 */
@RestController
public class ConsulOrderController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consul/order/{id}")
    public CommonResult consulOrder(@PathVariable("id")Integer id) {
        ResponseEntity<CommonResult> responseEntity = restTemplate.
                getForEntity("http://consul-provider-peyment/consul/request/" + id, CommonResult.class);
        System.out.println(responseEntity.toString());
        if (!responseEntity.getStatusCode().is2xxSuccessful())
            return new CommonResult<>(444, "consul order error.");
        return responseEntity.getBody();
    }

}
