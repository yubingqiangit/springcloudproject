package com.yu;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.yu.rebbitmqConfig.MyProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/24 14:30 
 */
@SpringBootApplication
@EnableEurekaClient
@EnableBinding(value = {MyProcessor.class})
@MapperScan("com.yu.mybatis.mapper")
public class PaymentApplication8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8002.class, args);
    }

    /**
     * 分页
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
