package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.dtos.RegisterDTO;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<SucessResponse> register(@Valid @RequestBody RegisterDTO registerDTO) {
        Users createdUser = authService.register(registerDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(createdUser.getUserId())
                .message("Usuário criado com sucesso!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }


}
