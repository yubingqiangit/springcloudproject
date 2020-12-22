package com.yu.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

/**
 * 订阅消息，并对消息进行处理
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 15:34 
 */
/*@Component
public class DefaultMessageListener {
    private Logger logger = LoggerFactory.getLogger(DefaultMessageListener.class);


    //表示对消息进行订阅监控
    @StreamListener(Processor.INPUT)
    public void processMyMessage(String message) {
        logger.info("接收到消息：" + message);
    }
}*/
