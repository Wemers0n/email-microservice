package com.ms.userservice.core.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.userservice.core.dtos.EmailDTO;
import com.ms.userservice.core.services.EmailService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api/send")
@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping()
    public ResponseEntity send(@RequestBody EmailDTO dto){
        this.emailService.sendMessage(dto);
        return ResponseEntity.ok().body("message sent");
    } 
}
