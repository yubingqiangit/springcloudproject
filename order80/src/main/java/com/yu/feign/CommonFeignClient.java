package com.yu.feign;

import com.yu.commonApi.FeignCommonService;
import com.yu.config.FeignConfigruation;
import com.yu.fallBack.FeignHystrixFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 对feign的集成
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/28 17:30 
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",configuration = FeignConfigruation.class,fallback = FeignHystrixFallBackImpl.class)
public interface CommonFeignClient extends FeignCommonService {

}
