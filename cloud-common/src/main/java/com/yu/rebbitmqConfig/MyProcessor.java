package com.yu.rebbitmqConfig;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * 自定义消息通道接口::消息生产者和消费者
 * @version 1.0
 * @author yubingqian
 * @date 2020/12/18 17:03 
 */
@Component
public interface MyProcessor {

    String MESSAGE_INPUT = "log_input";

    String MESSAGE_OUTPUT = "log_output";

    String LOG_FORMAT_INPUT = "log_format_input";

    String LOG_FORMAT_OUTPUT = "log_format_output";

    String MODEL_MESSAGE_INPUT = "model_input";

    String MODEL_MESSAGE_OUTPUT = "model_output";



    @Input(MESSAGE_INPUT)
    SubscribableChannel logInput();

    @Output(MESSAGE_OUTPUT)
    MessageChannel logOutput();

    @Input(MODEL_MESSAGE_INPUT)
    SubscribableChannel modelInput();

    @Output(MODEL_MESSAGE_OUTPUT)
    MessageChannel modelOutput();

    @Input(LOG_FORMAT_INPUT)
    SubscribableChannel logFormatInput();

    @Output(LOG_FORMAT_OUTPUT)
    MessageChannel logFormatOutput();

}
