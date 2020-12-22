package com.yu.rabbitmq;

import com.yu.rebbitmqConfig.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 消息接收服务
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/21 9:00
 */
@Component
public class LogMessageListener {
    Logger logger = LoggerFactory.getLogger(LogMessageListener.class);
    /**
     * 通过 MyProcessor.MESSAGE_INPUT 接收消息
     * 有返回值时接收到消息并再次推送
     * 然后通过 SendTo 将处理后的消息发送到 MyProcessor.LOG_FORMAT_OUTPUT
     * @param message
     * @return
     */
    @StreamListener(MyProcessor.MESSAGE_INPUT)
    //@SendTo(MyProcessor.LOG_FORMAT_OUTPUT)
    public void processLogMessage(String message) {
        logger.info("接收到原始消息：" + message);
        //return "「" + message +"」";
    }

    /**
     * 接收来自 MyProcessor.LOG_FORMAT_INPUT 的消息
     * 也就是加工后的消息，也就是通过上面的 SendTo 发送来的
     * 因为 MyProcessor.LOG_FORMAT_OUTPUT 和 MyProcessor.LOG_FORMAT_INPUT 是指向同一 exchange
     * @param message
     */
    @StreamListener(MyProcessor.LOG_FORMAT_INPUT)
    public void processFormatLogMessage(String message) {
        logger.info("接收到格式化后的消息：" + message);
    }


    @StreamListener(MyProcessor.MODEL_MESSAGE_INPUT)
    public void processModelMessage(String message) {
        logger.info("MODEL_MESSAGE_INPUT接收到格式化后的消息：" + message);
    }
}
