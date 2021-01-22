package com.yu.config.rocketmqConfig;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ生产者配置类
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 9:11 
 */
@Slf4j
@Data
@Configuration
public class MQProducerConfigure {

    /**
     * 服务名称
     */
    @Value("${spring.rocketmq.consumer.groupName}")
    private String groupName;
    /**
     * 服务地址
     */
    @Value("${spring.rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    /**
     * 消息最大值
     */
    @Value("${spring.rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize;
    /**
     * 消息发送超时时间
     */
    @Value("${spring.rocketmq.producer.sendMsgTimeOut}")
    private Integer sendMsgTimeOut;
    /**
     * 失败重试次数
     */
    @Value("${spring.rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;

    /**
     * mq 生成者配置
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQProducer defaultProducer() throws MQClientException {
        log.info("------------------------defaultProducer begin init---------------------------------------");
        DefaultMQProducer producer = new DefaultMQProducer("cloud-order-service");
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeOut);
        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendFailed);
        producer.start();
        log.info("-------------------------defaultProducer init success---------------------------------  ");
        return producer;
    }
}
