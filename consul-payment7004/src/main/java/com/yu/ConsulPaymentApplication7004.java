package com.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/25 9:13 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulPaymentApplication7004 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentApplication7004.class, args);
    }
}
