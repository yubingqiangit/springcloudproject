package com.yu.controller;

import com.alibaba.fastjson.JSON;
import com.yu.MybatisPuls;
import com.yu.entity.ModelStakeRel;
import com.yu.service.ModelStakeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/18 10:51 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MybatisPuls.class)
public class MybatisPlusController {

    @Autowired
    ModelStakeService modelStakeService;


    @Test
    public void test() {
        ModelStakeRel modelStakeRel = modelStakeService.selectById(3);
        System.out.println(modelStakeRel.getStakeNo());
        System.out.println( "===============查询结果" + JSON.toJSON(modelStakeRel));
        ModelStakeRel modelStakeRel1 = new ModelStakeRel();
        modelStakeRel1.setStakeNo("22323323333");
        modelStakeRel1.setModelId("999999999999");
        boolean insert = modelStakeService.insert(modelStakeRel1);
        System.out.println(insert);
    }
}
