package com.yu.rabbitmq.controller;

import com.yu.rebbitmqConfig.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/21 9:06 
 */
@RestController
@EnableBinding(value = {MyProcessor.class})
public class MyMessageController {
    Logger logger = LoggerFactory.getLogger(MyMessageController.class);

    @Autowired
    private MyProcessor myProcessor;

    /**
     * 支付宝异步回调成功发送mq消息通知订单中心
     * @param message
     */
    @GetMapping(value = "modelOutput")
    public void modelOutput(String message){
        logger.info("modelOutput message::{}",message);
        Message<String> stringMessage = org.springframework.messaging.support.MessageBuilder.withPayload(message).build();
        logger.info("modelOutput rabbitMq stringMessage::{}",stringMessage);
        boolean send = myProcessor.modelOutput().send(stringMessage);
        System.out.println("modelOutput send result::" + send);
    }
}
