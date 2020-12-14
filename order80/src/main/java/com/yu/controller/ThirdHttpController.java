package com.yu.controller;

import com.yu.common.CommonResult;
import com.yu.feign.HttpFeignGetService;
import com.yu.feign.HttpFeignPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 实现feign调用第三方http接口
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/14 9:54 
 */
@RestController
public class ThirdHttpController {
    private Logger logger = LoggerFactory.getLogger(ThirdHttpController.class);
    @Resource
    private HttpFeignGetService httpFeignService;

    @Autowired
    private HttpFeignPostService httpFeignPostService;



    @RequestMapping(value = "/http/get",method = RequestMethod.GET)
    public CommonResult<List<String>> httpGet() {
        List<String> allUrl = httpFeignService.getAllUrl();
       logger.info(allUrl.toArray().toString());
        return new CommonResult<List<String>>(allUrl);
    }

    @RequestMapping(value = "/http/post",method = RequestMethod.POST)
    public CommonResult<Object> httpPost(HttpServletRequest request) {
        String page = request.getParameter("page");
        String count = request.getParameter("count");
        logger.info("method httpPost page::{} count::{}",page,count);
        String images = httpFeignPostService.getImages(page,count);
        logger.info("method httpPost response::{}",images);
        return new CommonResult<>(images);
    }
}
