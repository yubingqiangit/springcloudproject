package com.yu;
import com.yu.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/27 15:37 
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class CloudZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudZuulApplication.class, args);
    }

    // 实例化tokenfilter,否则网关不生效
    @Bean
    TokenFilter tokenFilter(){
        return new TokenFilter();
    }
}
