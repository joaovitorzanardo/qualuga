package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.dtos.UserAccountDTO;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user/account")
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserAccountDTO> getUserAccount(@PathVariable Long userId) {
        Users user = usersService.getUser(userId);
        return ResponseEntity.ok(user.toDTO());
    }

    @PutMapping
    public ResponseEntity<SucessResponse> updateUserAccount(@Valid @RequestBody UserAccountDTO userAccountDTO) {
        Users user = usersService.updateUser(userAccountDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(userAccountDTO.getUserId())
                .message("Usuário atualizado com sucesso!")
                .httpStatus(HttpStatus.OK).build();

        return ResponseEntity.ok(sucessResponse);
    }

}
