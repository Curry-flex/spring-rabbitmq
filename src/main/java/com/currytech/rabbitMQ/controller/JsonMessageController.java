package com.currytech.rabbitMQ.controller;

import com.currytech.rabbitMQ.Producer.RabbitMQJsonMessageProducer;
import com.currytech.rabbitMQ.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/message/json")
public class JsonMessageController {

    @Autowired
    private RabbitMQJsonMessageProducer rabbitMQJsonMessageProducer;

    @PostMapping
    public ResponseEntity<User> sendMessage(@RequestBody User user)
    {
        ResponseEntity<User> response =  rabbitMQJsonMessageProducer.sendJSONMessage(user);

        return response;
    }
}
