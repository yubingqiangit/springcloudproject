package com.yu.config;/**
 *
 * @author yubingqian
 * @date 2020-09-24 17:28
 *
 */

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/24 17:28 
 */
@Component
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
