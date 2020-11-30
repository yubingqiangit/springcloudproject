package com.yu.feign;

import com.yu.common.CommonResult;
import com.yu.config.FeignConfigruation;
import com.yu.fallBack.FeignHystrixFallBackImpl;
import com.yu.model.ModelStakeRel;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 使用自定义feign配置 FeignConfigruation
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/28 15:04 
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",configuration = FeignConfigruation.class,fallback = FeignHystrixFallBackImpl.class)
public interface FeignConfigruationService {

    @RequestMapping(value = "/post/list")
    public CommonResult<List<ModelStakeRel>> getModelPostList(@RequestBody ModelStakeRel modelStakeRel);
}
