package com.currytech.rabbitMQ.Producer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.plaf.TableHeaderUI;

@Service
public class RabbitMQProducer {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value(("${rabbitmq.routingkey.name}"))
    private  String routingKey;

    private RabbitTemplate rabbitTemplate;

    //Inject Rabbit Template Bean
    public  RabbitMQProducer(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String sendMessage( String message)
    {
        LOGGER.info(String.format("Message sent is -> %s" , message));
        rabbitTemplate.convertAndSend(exchangeName,routingKey,message);

        return "Operation successfully";
    }




}
