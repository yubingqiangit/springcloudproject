package com.yu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/5 14:37 
 */
@RestController
public class ThyemController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
