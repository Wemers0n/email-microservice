package com.ms.userservice.core.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.mail-topic}")
    private String emailTopic;

    public void sendMessage(String payload){
        try{
            log.info("Sending message to topic {} with data {}", emailTopic, payload);
            kafkaTemplate.send(emailTopic, payload);
        } catch (Exception e){
            log.error("Error trying to send data to topic {} with data {}", emailTopic, payload, e);
        }
    }
}
