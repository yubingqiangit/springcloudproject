package com.yu;/**
 *
 * @author yubingqian
 * @date 2020-11-27 9:38
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/27 9:38 
 */
@SpringBootApplication
@RefreshScope
public class CloudConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigClientApplication.class, args);
    }
}
