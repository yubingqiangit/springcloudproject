package com.yu;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/18 10:33 
 */

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.yu.mapper")
public class MybatisPuls {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPuls.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
