package com.ms.userservice.core.controllers;

import com.ms.userservice.core.dtos.UserDTO;
import com.ms.userservice.core.models.User;
import com.ms.userservice.core.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO dto){
        var user = this.userService.saveUser(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
