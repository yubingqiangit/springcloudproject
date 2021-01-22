package com.yu.rocketmqConfig;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 11:47 
 */
public class ObserverMQ {
    private static List<PassMessage> passMessages = new ArrayList<>();

    public void addPassMassage(PassMessage passMessage) {
        passMessages.add(passMessage);
    }

    public String passMessage(List<MessageExt> msgList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        passMessages.stream().forEach(x -> {
            x.passMessage(msgList, consumeConcurrentlyContext);
        });
        return "success";
    }

}
