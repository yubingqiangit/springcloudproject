package com.yu.commonApi;

import com.yu.common.CommonResult;
import com.yu.model.ModelStakeRel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 公用基础接口
 * feign对集成的支持，服务提供者和消费者共用改api
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/28 17:16 
 */
public interface FeignCommonService {

    @RequestMapping(value = "/common/model/{id}",method = RequestMethod.GET)
    CommonResult<ModelStakeRel> commonResult(@PathVariable("id") Integer id);

}
