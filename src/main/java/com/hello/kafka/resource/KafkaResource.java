package com.hello.kafka.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaResource {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    private static final String topic = "hi.kafka";

    @GetMapping("/publish/message/{message}")
    public String publishMessage(@PathVariable("message") String message) {
        kafkaTemplate.send(topic,message);
        return "Message : " + message + " published sucessfully";
    }

}
