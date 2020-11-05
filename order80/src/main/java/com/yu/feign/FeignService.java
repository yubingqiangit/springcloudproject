package com.yu.feign;

import com.yu.common.CommonResult;
import com.yu.fallBack.FeignHystrixFallBackImpl;
import com.yu.model.ModelStakeRel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/23 10:18 
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback = FeignHystrixFallBackImpl.class)
public interface FeignService {
    @GetMapping("/get")
    public CommonResult<ModelStakeRel> getCount();

    @GetMapping("/mybatis/model/{id}")
    public CommonResult<ModelStakeRel> getForMybatis(@PathVariable("id") Integer id);
}
