package com.yu;/**
 *
 * @author yubingqian
 * @date 2020-09-24 16:54
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/24 16:54 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulOrderApplication8080 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulOrderApplication8080.class, args);
    }
}
