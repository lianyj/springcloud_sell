package com.springcloud.orderserver.message;

import com.alibaba.fastjson.JSON;
import com.springcloud.productcommon.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lyj
 * @since: 2019/2/1
 */
@Component
@Slf4j
@Transactional
public class ProductInfoReceiver {

    private static final  String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //1.@RabbitListener(queues = "myQueue")
    //2.自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    //3.自动创建,exchange和queue绑定  @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue2"),exchange = @Exchange("myExchange2")
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
        //message => ProductInfoOutput
        List<ProductInfoOutput> productInfoOutputs = JSON.parseArray(message,ProductInfoOutput.class);

        //存储到redis中
        for (ProductInfoOutput productInfoOutput:productInfoOutputs){
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,productInfoOutput.getProductId()),productInfoOutput.getProductStock().toString());
        }


        log.info("从队列{}接收到消息： {}","productInfo",productInfoOutputs);
    }
}
