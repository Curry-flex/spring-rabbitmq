package com.currytech.rabbitMQ.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabitMQConsumer {

    private static  final Logger LOGGER = LoggerFactory.getLogger(RabitMQConsumer.class);

    //Read Message from QUEUE
    @RabbitListener(queues = "${rabbitm1.queue.name}")
    public void consume(String message)
    {
        LOGGER.info(String.format("Message from queue %s", message));
    }
}
