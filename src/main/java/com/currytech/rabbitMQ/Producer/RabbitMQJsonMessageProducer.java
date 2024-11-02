package com.currytech.rabbitMQ.Producer;


import com.currytech.rabbitMQ.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonMessageProducer {

    private  static  final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonMessageProducer.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value(("${rabbitmq.routingkey.name.json}"))
    private  String routingKey;

    @Autowired
    private AmqpTemplate amqpTemplate;


    private RabbitTemplate rabbitTemplate;

    //Inject Rabbit Template Bean
    public  RabbitMQJsonMessageProducer(RabbitTemplate rabbitTemplate)
    {
        this.rabbitTemplate = rabbitTemplate;
    }

    public ResponseEntity<User> sendJSONMessage(User user)
    {

        User  userObj = new User();
        userObj.setId(user.getId());
        userObj.setFname(user.getFname());
        userObj.setLname(user.getLname());

        //System.out.println("User object sent " + userObj);
        LOGGER.info(String.format("Message sent is "+ userObj));
        amqpTemplate.convertAndSend(exchangeName,routingKey,userObj);


        return new ResponseEntity<>(userObj, HttpStatus.OK);
    }
}
