package com.ms.userservice.core.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmailDTO {
    
    private UUID userId;
    private String emailTo;
    private String subject;
    private String text;
}
