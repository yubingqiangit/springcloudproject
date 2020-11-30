package com.yu.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RefreshScope注解实现手动刷新，rebbitmq刷新client value
 * 如果远程修改git配置文件，config server 请求http://localhost:3301/cloud-config-client/dev/main直接生效
 * 但是config client 并不直接生效，需要POST请求http://localhost:3301/actuator/bus-refresh手动刷新，再次请求config client
 * 才会生效
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/30 11:51 
 */
@RestController
@RefreshScope
public class GitRefreshConfigController  {
    @Value("${data.env}")
    private String env;

    @Value("${data.user.username}")
    private String username;

    @Value("${data.user.password}")
    private String password;


    @GetMapping(value = "auto")
    public Object auto(){
        System.out.println("autoshow============" + env);
        return env + username + password;
    }

}
