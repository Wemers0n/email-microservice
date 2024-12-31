package com.ms.userservice.core.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ms.userservice.configs.utils.JsonUtil;
import com.ms.userservice.core.dtos.EmailDTO;
import com.ms.userservice.core.models.User;
import com.ms.userservice.core.producer.KafkaProducer;
import com.ms.userservice.core.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;
    private final JsonUtil jsonUtil;
        
    public void sendMessage(EmailDTO dto){

        var user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        var emailMessage = EmailDTO.builder()
        .userId(user.getId())
        .emailTo(user.getEmail())
        .subject(dto.getSubject())
        .text(dto.getText())
        .build();

        kafkaProducer.sendMessage(jsonUtil.toJson(emailMessage));
    }

    public EmailDTO message(User user){
        var mail = EmailDTO.builder()
        .userId(user.getId())
        .emailTo(user.getEmail())
        .subject("Cadastro Realizado com sucesso!")
        .text(user.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!")
        .build();
        return mail;
    }
}
