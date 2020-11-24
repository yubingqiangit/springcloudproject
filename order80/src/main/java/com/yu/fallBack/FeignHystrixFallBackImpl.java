package com.yu.fallBack;

import com.yu.common.CommonResult;
import com.yu.feign.FeignService;
import com.yu.model.ModelStakeRel;
import org.springframework.stereotype.Component;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/27 11:51 
 */
@Component
public class FeignHystrixFallBackImpl implements FeignService {
    @Override
    public CommonResult<ModelStakeRel> getCount() {
        System.out.println("************************openfeign-hystrix-getCount****************************");
        return new CommonResult<>(404,"method get count openfeign hystrix error.");
    }

    @Override
    public CommonResult<ModelStakeRel> getForMybatis(Integer id) {
        System.out.println("************************openfeign-hystrix-getForMybatis****************************");
        return new CommonResult<>(405,"method getForMybatis openfeign hystrix error.");
    }

    @Override
    public CommonResult<ModelStakeRel> getModelByParams(Integer id, String modelId) {
        System.out.println("************************openfeign-hystrix-getForMybatis****************************");
        return new CommonResult<>(405,"method getModelByParams openfeign hystrix error.");
    }

    /**
     * feign构造多参post请求
     * @param modelStakeRel
     * @return
     */
    @Override
    public CommonResult<ModelStakeRel> getModlePost(ModelStakeRel modelStakeRel) {
        System.out.println("************************openfeign-hystrix-getForMybatis****************************");
        return new CommonResult<>(405,"method getModlePost openfeign hystrix error.");
    }
}
