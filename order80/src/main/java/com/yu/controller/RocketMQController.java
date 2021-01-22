package com.yu.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 10:17 
 */
@RestController
public class RocketMQController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @RequestMapping(value = "/rocket/mq",method = RequestMethod.GET)
    public String test_rocketmq() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message();
        message.setTopic("MyTopic");
        message.setTags("TestTag");
        message.setBody("hello rocketmq".getBytes());

        System.out.println("===============" + defaultMQProducer);
        SendResult send = defaultMQProducer.send(message);
        System.out.println(send);
        return "success";
    }
}
