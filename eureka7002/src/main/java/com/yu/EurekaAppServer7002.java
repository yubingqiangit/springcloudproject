package com.yu;/**
 *
 * @author yubingqian
 * @date 2020-09-22 15:18
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/22 15:18 
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaAppServer7002 {
    public static void main(String[] args) {
            SpringApplication.run(EurekaAppServer7002.class, args);
    }
}
