package com.springcloud.orderserver;

import com.springcloud.orderserver.message.StreamClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServerApplicationTests {

    @Test
    public void contextLoads() {
    }


}

