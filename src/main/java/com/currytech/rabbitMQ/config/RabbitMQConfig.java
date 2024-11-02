package com.currytech.rabbitMQ.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //spring bean for rabbitmq queue
    @Value("${rabbitm1.queue.name}")
    private String queueName;

    @Value("${rabbitm1.queue.name.json}")
    private String queueJsosnMessage;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value(("${rabbitmq.routingkey.name}"))
    private  String routingKey;

    @Value(("${rabbitmq.routingkey.name.json}"))
    private  String routingKeyJson;


    //QUEUE
    @Bean
    public Queue queue()
    {
        return  new Queue(queueName);
    }

    @Bean
    public Queue queueJson()
    {
        return  new Queue(queueJsosnMessage);
    }

    //EXCHANGE
    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(exchangeName);
    }

    //BINDING
    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }


    //BINDING FOR JSON
    @Bean
    public  Binding bindingJson()
    {
        return  BindingBuilder.bind(queueJson())
                .to(exchange())
                .with(routingKeyJson);
    }


    //Support Json message communication
    @Bean
    public MessageConverter converter()
    {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        return  rabbitTemplate;
    }


}
