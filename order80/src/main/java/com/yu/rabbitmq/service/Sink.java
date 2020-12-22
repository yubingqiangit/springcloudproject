package com.yu.rabbitmq.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * mq消息接收通道
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 15:30 
 */
public interface Sink {
    String INPUT = "input";

    @Input("input")
    SubscribableChannel input();
}
