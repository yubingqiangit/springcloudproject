package com.yu.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/16 16:10 
 */
@Configuration
public class RedisRateLimiterConfig {

    /**
     * 根据ip限流
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        System.out.println("=====================ipKeyResolver=====================");
        return exchange -> Mono.just(
                exchange.getRequest().getRemoteAddress().getHostName());
    }
}
