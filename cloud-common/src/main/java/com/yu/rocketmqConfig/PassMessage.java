package com.yu.rocketmqConfig;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 抽象观察者，如果订单mq消息需要实现该接口
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 11:39 
 */
public interface PassMessage {

    void passMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext consumeConcurrentlyContext);
}
