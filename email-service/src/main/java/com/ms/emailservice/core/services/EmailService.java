package com.ms.emailservice.core.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ms.emailservice.core.dtos.EmailDTO;
import com.ms.emailservice.core.enums.EStatusEmail;
import com.ms.emailservice.core.models.Email;
import com.ms.emailservice.core.respositories.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public void sendEmail(EmailDTO dto){
        var email = this.saveEmail(dto);
        try{           
            isSentEmail(dto);
            email.setStatusEmail(EStatusEmail.SEND);
            this.save(email);

        } catch (Exception e){
            log.error("Error Error when sending email", e);
            email.setStatusEmail(EStatusEmail.ERROR);
        }
    }

    private void isSentEmail(EmailDTO dto) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(emailFrom);
        helper.setTo(dto.emailTo());
        helper.setSubject(dto.subject());
        helper.setText(dto.text());

        mailSender.send(message);
    }

    private Email saveEmail(EmailDTO dto){
        var email = Email.builder()
        .userId(dto.userId())
        .emailForm(emailFrom)
        .emailTo(dto.emailTo())
        .subject(dto.subject())
        .text(dto.text())
        .build();

        this.save(email);

        return email;
    }

    private void save(Email email){
        this.emailRepository.save(email);
    }

}
