package com.ms.emailservice.core.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ms.emailservice.core.enums.EStatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_emails")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    private String emailForm;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    @Enumerated(EnumType.STRING)
    private EStatusEmail statusEmail;

    @PrePersist
    public void prePersist(){
        sendDateEmail = LocalDateTime.now();
        statusEmail = EStatusEmail.PENDING;
    }

    @PreUpdate
    public void preUpdate(){
        sendDateEmail = LocalDateTime.now();
    }
}
