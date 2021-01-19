package com.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * springcloud 集成consul
 * windows 使用开发者模式启动consul命令：consul agent -dev
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/24 16:28 
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class}) //排除mysql和redis的自动化配置
@EnableDiscoveryClient
public class ConsulPaymentApplication7003 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentApplication7003.class,args);
    }
}
