package com.yu.rabbitmq;

import com.yu.rebbitmqConfig.MyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
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
    @StreamListener(MyProcessor.MODEL_MESSAGE_INPUT)
    public void processModelMessage(String message) {
        logger.info("接收到支付中心异步支付结果MQ通知：" + message);
    }
}
