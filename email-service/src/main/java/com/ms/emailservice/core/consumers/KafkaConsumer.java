package com.ms.emailservice.core.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ms.emailservice.configs.utils.JsonUtil;
import com.ms.emailservice.core.services.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final EmailService emailService;
    private final JsonUtil jsonUtil;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.mail-topic}"
    )
    public void consumeSuccessMessage(String payload){
        log.info("Receiving success message {} from emailTopic", payload);
        var message = jsonUtil.toEmail(payload);
        this.emailService.sendEmail(message);
    }

    
}
