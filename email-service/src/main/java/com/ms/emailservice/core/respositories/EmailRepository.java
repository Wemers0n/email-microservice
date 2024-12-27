package com.ms.emailservice.core.respositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.emailservice.core.models.Email;

public interface EmailRepository extends JpaRepository<Email, UUID>{

}
