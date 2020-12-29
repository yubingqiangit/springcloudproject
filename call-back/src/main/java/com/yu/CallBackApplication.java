package com.yu;

import com.yu.rebbitmqConfig.MyProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 支付回调
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/29 9:59 
 */
//@EnableHystrix
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@EnableHystrixDashboard
@EnableBinding(value = {MyProcessor.class})
@MapperScan("com.yu.dao")
public class CallBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(CallBackApplication.class);
    }
}
