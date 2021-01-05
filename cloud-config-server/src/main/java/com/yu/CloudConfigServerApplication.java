package com.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心服务端
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/27 9:23 
 */
@SpringBootApplication
@EnableConfigServer
public class CloudConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class, args);
    }
}
