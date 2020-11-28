package com.yu.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/27 9:40 
 */
@Data
@Component
public class GitConfig {
    @Value("${data.env}")
    private String env;

    @Value("${data.user.username}")
    private String username;

    @Value("${data.user.password}")
    private String password;

}
