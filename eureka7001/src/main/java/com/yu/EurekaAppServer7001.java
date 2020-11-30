package com.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/22 14:59 
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaAppServer7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaAppServer7001.class, args);
    }
}
