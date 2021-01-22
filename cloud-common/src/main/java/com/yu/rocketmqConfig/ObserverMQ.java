package com.yu.rocketmqConfig;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者转发
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 11:47 
 */
public class ObserverMQ {

    /**
     * 观察者集合
     */
    private static List<PassMessage> passMessages = new ArrayList<>();

    /**
     * 添加观察者
     * @param passMessage
     */
    public void addPassMassage(PassMessage passMessage) {
        for (PassMessage message : passMessages) {
            //该处判断消费者对象名称是否重复，如果已经存在的话，不再进行添加
            if (message.getClass().getSimpleName().equals(passMessage.getClass().getSimpleName())) {
                return;
            }
        }
        passMessages.add(passMessage);
    }

    /**
     * 转发消息
     * @param msgList
     * @param consumeConcurrentlyContext
     * @return
     */
    public String passMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        passMessages.stream().forEach(x -> {
            x.passMessage(msgList, consumeConcurrentlyContext);
        });
        return "success";
    }

}
