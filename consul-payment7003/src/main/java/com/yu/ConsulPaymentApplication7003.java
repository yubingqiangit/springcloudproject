package com.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * springcloud 集成consul
 * windows 使用开发者模式启动consul命令：consul agent -dev
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/24 16:28 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulPaymentApplication7003 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentApplication7003.class,args);
    }
}
