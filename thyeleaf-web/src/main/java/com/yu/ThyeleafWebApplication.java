package com.yu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * TODO
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/1 15:59 
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ThyeleafWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThyeleafWebApplication.class, args);
    }
}
