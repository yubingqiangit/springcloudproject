package com.yu;

import com.yu.config.HostAddKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/27 17:52 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GateWayApplication7300 {


    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication7300.class, args);
    }


   /* @Bean(name = HostAddKeyResolver.BEAN_NAME)
    public HostAddKeyResolver hostAddKeyResolver() {
        return new HostAddKeyResolver();
    }*/
}
