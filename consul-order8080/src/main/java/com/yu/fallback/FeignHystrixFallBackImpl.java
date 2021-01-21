package com.yu.fallback;

import com.yu.common.CommonResult;
import com.yu.feign.FeignService;
import com.yu.model.ModelStakeRel;
import com.yu.model.PayReqItem;
import com.yu.model.PayRespItem;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * feign容错处理
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
        System.out.println("************************openfeign-hystrix-getModelByParams****************************");
        return new CommonResult<>(405,"method getModelByParams openfeign hystrix error.");
    }

    /**
     * feign构造多参post请求
     * @param modelStakeRel
     * @return
     */
    @Override
    public CommonResult<ModelStakeRel> getModlePost(ModelStakeRel modelStakeRel) {
        System.out.println("************************openfeign-hystrix-getModlePost****************************");
        return new CommonResult<>(405,"method getModlePost openfeign hystrix error.");
    }

    @Override
    public CommonResult<List<ModelStakeRel>> getModelPostList(ModelStakeRel modelStakeRel) {
        System.out.println("************************openfeign-hystrix-getModelPostList****************************");
        return new CommonResult<>(405,"method getModelPostList openfeign hystrix error.");
    }



    /**
     * 支付
     * @param payItem
     * @return
     */
    @Override
    public CommonResult<PayRespItem> pay(PayReqItem payItem) {
        System.out.println("************************openfeign-hystrix-pay****************************");
        return  new CommonResult<PayRespItem>(405,"method pay openfeign hystrix error.");
    }

    @Override
    public CommonResult<PayRespItem> payGet(String orderNo, String amount) {
        System.out.println("************************openfeign-hystrix-payGet****************************");
        return  new CommonResult<PayRespItem>(405,"method pay openfeign hystrix error.");
    }

    /**
     * App预下单支付
     * @param payItem
     * @return
     */
    @Override
    public CommonResult<String> qrStr(PayReqItem payItem) {
        System.out.println("************************openfeign-hystrix-qrStr****************************");
        return  new CommonResult<String>(405,"method qrStr openfeign hystrix error.");
    }

    /**
     * 生成二维码
     * @param payItem
     * @return
     */
    @Override
    public CommonResult<String> createQrCode(PayReqItem payItem) {
        System.out.println("************************openfeign-hystrix-createQrCode****************************");
        return  new CommonResult<String>(405,"method createQrCode openfeign hystrix error.");
    }
}
