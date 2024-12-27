package com.ms.userservice.core.repositories;

import com.ms.userservice.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
