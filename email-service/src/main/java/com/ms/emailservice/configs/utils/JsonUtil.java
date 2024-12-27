package com.ms.emailservice.configs.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.emailservice.core.dtos.EmailDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String toJson(Object object){
        try{
            return objectMapper.writeValueAsString(object);
        } catch (Exception e){
            return "";
        }
    }

    public EmailDTO toEmail(String json){
        try{
            return objectMapper.readValue(json, EmailDTO.class);
        } catch(Exception e){
            return null;
        }
    }
}
