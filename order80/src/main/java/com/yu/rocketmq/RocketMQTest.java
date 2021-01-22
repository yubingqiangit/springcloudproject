package com.yu.rocketmq;

import com.yu.OrderApp80;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * rocketmq测试类
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 9:22 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderApp80.class)
public class RocketMQTest {
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Test
    public void test_rocketmq() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message();
        message.setTopic("MyTopic");
        message.setTags("TestTag");
        message.setBody("hello rocketmq".getBytes());

        System.out.println("===============" + defaultMQProducer);
        SendResult send = defaultMQProducer.send(message);
        System.out.println(send);

    }
}
