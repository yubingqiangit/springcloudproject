package com.yu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/27 15:05 
 */
@RestController
public class HystrixDashBoardController {

    @GetMapping("/test/dashboard/{id}")
    public String testDashBoard(@PathVariable("id") Integer id) {
        return "test dashboard.";
    }

}
