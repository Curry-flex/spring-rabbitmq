package com.currytech.rabbitMQ.consumer;


import com.currytech.rabbitMQ.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JsonComsumer {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(JsonComsumer.class);

    @RabbitListener(queues = "${rabbitm1.queue.name.json}")
    public void redJsonMessage(User user)
    {
        LOGGER.info("Message from Json Queue " + user.toString());
    }

}
