package com.yu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/22 15:35 
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yu.dao")
public class PaymentApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8001.class, args);
    }
}
