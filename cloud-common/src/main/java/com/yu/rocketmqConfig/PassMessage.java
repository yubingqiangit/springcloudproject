package com.yu.rocketmqConfig;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 11:39 
 */
public interface PassMessage {

    void passMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext consumeConcurrentlyContext);
}
