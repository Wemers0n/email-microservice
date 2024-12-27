package com.ms.userservice.core.services;

import com.ms.userservice.configs.utils.JsonUtil;
import com.ms.userservice.core.dtos.EmailDTO;
import com.ms.userservice.core.dtos.UserDTO;
import com.ms.userservice.core.models.User;
import com.ms.userservice.core.producer.KafkaProducer;
import com.ms.userservice.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaProducer kafkaProducer;
    private final JsonUtil jsonUtil;

    public User saveUser(UserDTO dto){
        var user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .build();
        // var email = message(user);
        userRepository.save(user);
        kafkaProducer.sendMessage(jsonUtil.toJson(message(user)));
        return user;
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
