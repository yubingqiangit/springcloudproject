package com.yu.rocketmqConfig;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ消费者配置类
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/22 9:16 
 */
@Slf4j
@Data
@Configuration
public class MQConsumerConfigure {

    /**
     * 服务名称
     */
    @Value("${spring.rocketmq.consumer.groupName}")
    private String groupName;
    /**
     * 服务地址
     */
    @Value("${spring.rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    /**
     * topic,tag配置
     */
    @Value("${spring.rocketmq.consumer.topics}")
    private String topics = "MyTopic~TestTag;*~*";
    /**
     * 消费者最小线程数据量
     */
    @Value("${spring.rocketmq.consumer.consumeThreadMin}")
    private Integer consumeThreadMin;
    /**
     * 消费者最大线程数据量
     */
    @Value("${spring.rocketmq.consumer.consumeThreadMax}")
    private Integer consumeThreadMax;

    @Value("${spring.rocketmq.consumer.consumeMessageBatchMaxSize}")
    private Integer consumeMessageBatchMaxSize;

    @Autowired
    private MQConsumeMsgListenerProcessor consumeMsgListenerProcessor;
    /**
     * mq 消费者配置
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQPushConsumer defaultConsumer() throws MQClientException {
        log.info("--------------------------[Consumer begin init]------------------------------");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        // 设置监听器
        consumer.registerMessageListener(consumeMsgListenerProcessor);
        /**
         * 设置consumer第一次启动是从队列头部开始还是队列尾部开始
         * 如果不是第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
//        consumer.setMessageModel(MessageModel.CLUSTERING);
        try {
            // 设置该消费者订阅的主题和tag，如果订阅该主题下的所有tag，则使用*,
            String[] topicArr = topics.split(";");
            for (String tag : topicArr) {
                String[] tagArr = tag.split("~");
               log.info("topic::" + tagArr[0] + " | tag::" + tagArr[1]);
                consumer.subscribe(tagArr[0], tagArr[1]);
            }
            consumer.start();
            log.info("--------------------------[Consumer init Success]------------------------------");
        } catch (MQClientException e) {
            log.error("consumer init error.",e);
        }
        return consumer;
    }
}
