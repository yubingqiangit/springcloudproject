package com.yu.rocketmq;

import com.yu.rocketmqConfig.PassMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 15:35 
 */
@Component
@Slf4j
public class Consumer implements PassMessage {

    @Override
    public void passMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (CollectionUtils.isEmpty(msgList)) {
            log.info("支付服务OrderConsumerMQ接收消息为空，直接返回成功");
        }
        MessageExt messageExt = msgList.get(0);
        log.info("支付服务OrderConsumerMQ接收到的消息为:::" + messageExt.toString());
        try {
            String topic = messageExt.getTopic();
            String tags = messageExt.getTags();
            String body = new String(messageExt.getBody(), "utf-8");

            log.info("支付服务MQ消息topic={}, tags={}, 消息内容={}", topic,tags,body);
        } catch (Exception e) {
            log.error("获取MQ消息内容异常{}",e);
        }
    }
}
