package com.yu.rabbitmq.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 定义MQ发送消息通道
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 15:29 
 */
public interface Source {
    String OUTPUT = "output";

    @Output("output")
    MessageChannel output();
}
