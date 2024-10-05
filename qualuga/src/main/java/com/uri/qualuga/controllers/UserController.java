package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.dtos.UserDTO;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        Users user = usersService.getUser(userId);
        return ResponseEntity.ok(user.toDTO());
    }

    @PutMapping
    public ResponseEntity<SucessResponse> updateUser(@Valid @RequestBody UserDTO userDTO) {
        Users user = usersService.updateUser(userDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(userDTO.getUserId())
                .message("Usuário atualizado com sucesso!")
                .httpStatus(HttpStatus.OK).build();
        return ResponseEntity.ok(sucessResponse);
    }


}
