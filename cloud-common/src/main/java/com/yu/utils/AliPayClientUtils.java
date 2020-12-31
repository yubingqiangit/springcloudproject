package com.yu.utils;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.yu.config.AlipayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 初始化工具类
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/31 15:09 
 */
public class AliPayClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(AliPayClientUtils.class);
    public static  AliPayClientUtils instance;

    public static AliPayClientUtils getInstance() {
        if (instance == null) {
            instance = new AliPayClientUtils();
        }
        return instance;
    }

    private AliPayClientUtils() {

    }

    public  AlipayClient alipayClient;

    public AlipayClient initAlipayClient(AlipayConfig alipayConfig) {
        System.out.println("Config::" + JSON.toJSONString(alipayConfig));
        if (alipayClient == null) {
            alipayClient  =  new DefaultAlipayClient(alipayConfig.gatewayUrl, alipayConfig.app_id, alipayConfig.merchant_private_key, "json", alipayConfig.charset, alipayConfig.alipay_public_key, alipayConfig.sign_type);;
            logger.info("==============AlipayClient初始化成功====================");
        }
        return alipayClient;
    }

}
