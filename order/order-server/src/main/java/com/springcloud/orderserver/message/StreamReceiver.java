package com.springcloud.orderserver.message;

import com.springcloud.orderserver.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * stream 接收端
 * @author: lyj
 * @since: 2019/2/1
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.OUTPUT)
    public Object processInput(Object message){
        log.info("input StreamReceiver :{}",message);
        return message;
    }

    @StreamListener(StreamClient.OUTPUT)
    public void processOutput(Object message){
        log.info("output StreamReceiver :{}",message);
    }


}
