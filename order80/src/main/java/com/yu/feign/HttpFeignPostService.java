package com.yu.feign;

import com.yu.config.FeignConfigruation;
import com.yu.fallBack.FeignHystrixFallBackImpl;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * feign调用外部http接口Post请求
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/14 10:06 
 */
@FeignClient(value = "service-post-api" , url = "${api.url}",
        configuration = FeignConfigruation.class,
        fallback = FeignHystrixFallBackImpl.class)
public interface HttpFeignPostService {

    //mvc注解
    //@RequestMapping(value = "/getImages?page={page}&count={count}",method = RequestMethod.POST)
    //String getImages(@PathVariable("page") String page, @PathVariable("count") String count);

    //feigin自带注解
    @RequestLine("POST /getImages?page={page}&count={count}")
    String getImages(@Param("page") String page, @Param("count") String count);
}
