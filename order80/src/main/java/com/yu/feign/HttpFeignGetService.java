package com.yu.feign;

import com.yu.common.CommonResult;
import com.yu.config.FeignConfigruation;
import com.yu.fallBack.FeignHystrixFallBackImpl;
import com.yu.model.ModelStakeRel;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * feign实现外部http接口Get请求
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/14 9:55 
 */
@FeignClient(value = "server-api",url = "${api.url}",
        configuration = FeignConfigruation.class,
        fallback = FeignHystrixFallBackImpl.class )
public interface HttpFeignGetService {


    //@RequestMapping(value = "/getAllUrl",method = RequestMethod.GET)  //mvc注解
    @RequestLine("GET /getAllUrl")   //feign自带注解
    List<String> getAllUrl();
}
