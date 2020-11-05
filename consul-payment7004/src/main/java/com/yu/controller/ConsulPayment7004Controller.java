package com.yu.controller;

import com.yu.common.CommonResult;
import com.yu.model.ModelStakeRel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/25 9:25 
 */
@RestController
public class ConsulPayment7004Controller {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consul/request/{id}")
    public CommonResult<ModelStakeRel> getRel(@PathVariable("id") Integer id) {
        System.out.println("consul producer serverport:" + serverPort);
        if (id == 222) {
            return new CommonResult<>(444, String.format("server port:[<%s>] return error.",serverPort));
        }
        ModelStakeRel modelStakeRel = new ModelStakeRel();
        modelStakeRel.setModelId("11111111111111");
        modelStakeRel.setRelTime(new Date());
        modelStakeRel.setStakeNo("222222222222222");
        return new CommonResult<>(modelStakeRel);
    }
}
