package com.currytech.rabbitMQ.controller;

import com.currytech.rabbitMQ.Producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Map<String,Object> request)
    {
      String message =  rabbitMQProducer.sendMessage(request.get("message").toString());

      return new ResponseEntity<>(message, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<String> sendMessage(@RequestParam("message") String message)
//    {
//        rabbitMQProducer.sendMessage(message);
//
//        return new ResponseEntity<>("Message has been sent ssuccessfully", HttpStatus.OK);
//    }
}
