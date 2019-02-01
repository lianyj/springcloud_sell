package com.springcloud.orderserver.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: lyj
 * @since: 2019/2/1
 */
public interface StreamClient {

    String INPUT = "input";
    String OUTPUT = "output";

    @Input(value = StreamClient.INPUT)
    SubscribableChannel input();


    @Output(value =StreamClient.OUTPUT)
    MessageChannel output();
}
