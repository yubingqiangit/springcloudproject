package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.common.CommonResult;
import com.yu.commonApi.FeignCommonService;
import com.yu.model.ModelStakeRel;
import com.yu.mybatis.service.ModelStakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * feign集成，但是存在耦合问题
 * @version 1.0
 * @author yubingqian
 * @date 2020/11/28 17:20 
 */
@RestController
public class CommonFeignController implements FeignCommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonFeignController.class);
    @Autowired
    private ModelStakeService modelStakeService;

    @Override
    @RequestMapping(value = "/common/model/{id}")
    public CommonResult<ModelStakeRel> commonResult(@PathVariable("id") Integer id) {
        System.out.println("request para id = " + id);
        //com.yu.entity.ModelStakeRel model = modelService.getModelStakeRelById(id);
        com.yu.mybatis.entity.ModelStakeRel target = modelStakeService.selectById(id);
        com.yu.entity.ModelStakeRel model = new com.yu.entity.ModelStakeRel();
        BeanUtils.copyProperties(target,model);
        logger.info("ModelStake查询结果::" + JSON.toJSONString(model));
        if (model == null) {
            return new CommonResult<ModelStakeRel>(444, String.format("server port::[<%s>]model is not found error.",8001));
        }
        System.out.println(model.toString());
        ModelStakeRel modelStakeRel = new ModelStakeRel();
        BeanUtils.copyProperties(model,modelStakeRel);
        return new CommonResult<>(modelStakeRel);
    }
}
