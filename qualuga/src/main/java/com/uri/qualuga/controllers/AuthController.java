package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.dtos.UsersDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @PostMapping(path = "/register")
    public ResponseEntity<SucessResponse> register(@Valid @RequestBody UsersDTO usersDTO) {

    }


}
