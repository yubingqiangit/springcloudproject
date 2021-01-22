package com.yu.rocketmqConfig;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消费监听者配置做消息转发
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 9:20 
 */
@Component
@Slf4j
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    @Value("${rocket_mq_consumer_path}")
    private String rocket_mq_consumer_path;
    /**
     * 默认msg里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
     * 不要抛异常，如果没有return CONSUME_SUCCESS ，consumer会重新消费该消息，直到return CONSUME_SUCCESS
     * @param msgList
     * @param consumeConcurrentlyContext
     * @return
     */
    @SneakyThrows
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        log.info("监听器获取消费者配置类：" + rocket_mq_consumer_path);
        ObserverMQ observerMQ = new ObserverMQ();
        String[] path = rocket_mq_consumer_path.split(";");
        for (String s : path) {
            Object o = Class.forName(s).newInstance();
            if (o instanceof PassMessage) {
                observerMQ.addPassMassage((PassMessage) o);
            }
        }
        String s =observerMQ.passMessage(msgList, consumeConcurrentlyContext);
        System.out.println("监听器转发成功....");
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
