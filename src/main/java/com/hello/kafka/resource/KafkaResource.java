package com.hello.kafka.resource;

import com.hello.kafka.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class KafkaResource {

    @Autowired
    KafkaTemplate<String, Post> kafkaTemplate;

    private static final String topic = "hi.kafka";

    @PostMapping("/publish/post")
    public String publishMessage(@RequestBody Post post) {
        kafkaTemplate.send(topic,post);
        return "Post with title : " + post.getTitle() + " published successfully";
    }

    @KafkaListener(topics = "post.kafka", groupId = "post-group",containerFactory = "postConcurrentKafkaListenerContainerFactory")
    public void consumePost(Post post) {
        System.out.println("Consumed: " + post);
    }

}
