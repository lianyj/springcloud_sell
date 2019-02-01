package com.springcloud.orderserver;

import com.springcloud.orderserver.dto.OrderDTO;
import com.springcloud.orderserver.message.StreamClient;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Date;

/**
 * 发送mq信息测试
 * @author: lyj
 * @since: 2019/1/31
 */
public class MqSenderTest extends OrderServerApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StreamClient streamClient;


    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue","now: "+new Date());
    }

    @Test
    public void sendOrder(){
        amqpTemplate.convertAndSend("myOrder","computer","now: "+new Date());
    }


    @Test
    public void process(){
        String message = "now "+ new Date();
        streamClient.output().send(
                MessageBuilder.withPayload(message).build()
        );
    }

}
