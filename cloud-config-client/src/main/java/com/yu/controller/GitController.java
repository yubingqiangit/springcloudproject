package com.yu.controller;

import com.yu.config.GitAutoRefreshConfig;
import com.yu.config.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/27 9:44 
 */
@RestController
public class GitController {
    @Autowired
    private GitConfig gitConfig;

    @Value("${data.env}")
    private String env;
    @Autowired
    private GitAutoRefreshConfig gitAutoRefreshConfig;

    @GetMapping(value = "show")
    public Object show(){
        System.out.println("show============" + env);
        return gitConfig;
    }

    @GetMapping(value = "autoShow")
    public Object autoShow(){
        return gitAutoRefreshConfig;
    }
}
