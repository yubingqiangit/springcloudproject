package com.yu.rabbitmq;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.model.PayReqItem;
import com.yu.mybatis.entity.PayPay;
import com.yu.mybatis.service.PayPayService;
import com.yu.rebbitmqConfig.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 接收支付中心异步通知mq
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/21 9:00
 */
@Component
public class LogMessageListener {
    Logger logger = LoggerFactory.getLogger(LogMessageListener.class);

    @Autowired
    PayPayService payPayService;

    /**
     * 通过 MyProcessor.MODEL_MESSAGE_INPUT 接收消息
     * 接收支付中心支付结果异步通知MQ
     * @param message
     * @return
     */
    @StreamListener(MyProcessor.MODEL_MESSAGE_INPUT)
    public void processModelMessage(String message) {
        logger.info("接收到支付中心异步支付结果MQ通知：" + message);
        //更新订单状态
        PayPay payPay = new PayPay();
        payPay.setState(20);
        payPay.setCommon("20");
        QueryWrapper<PayPay> wrapper = new QueryWrapper<PayPay>();
        wrapper.eq("orderNo", message);
        boolean update = payPayService.update(payPay, wrapper);
        logger.info("更新支付单状态成功......" + update);
    }
}
