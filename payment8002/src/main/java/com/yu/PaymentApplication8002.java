package com.yu;

import com.yu.rebbitmqConfig.MyProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/24 14:30 
 */
@SpringBootApplication
@EnableEurekaClient
@EnableBinding(value = {MyProcessor.class})
@MapperScan("com.yu.dao")
public class PaymentApplication8002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8002.class, args);
    }
}
