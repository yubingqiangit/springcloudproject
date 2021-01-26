package com.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/25 16:52 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosPayment {
    public static void main(String[] args) {
        SpringApplication.run(NacosPayment.class, args);
    }

}
