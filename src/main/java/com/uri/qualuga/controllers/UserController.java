package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.ScheduleDTO;
import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.dtos.UserAccountDTO;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.services.ScheduleService;
import com.uri.qualuga.services.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/myAccount")
public class UserController {

    @Autowired
    UsersService usersService;

    @Autowired
    ScheduleService scheduleService;

    @GetMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<UserAccountDTO> getMyAccount() {
        Users user = usersService.getLoggedUser();
        return ResponseEntity.ok(user.toDTO());
    }

    @PutMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> updateMyAccount(@Valid @RequestBody UserAccountDTO userAccountDTO) {
        Users user = usersService.updateUser(userAccountDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(userAccountDTO.getUserId())
                .message("Usuário atualizado com sucesso!")
                .httpStatus(HttpStatus.OK).build();

        return ResponseEntity.ok(sucessResponse);
    }

    @GetMapping(path = "/agenda")
    public ResponseEntity<List<ScheduleDTO>> getMyAgenda() {
        List<ScheduleDTO> myNextSchedules = scheduleService.getMyNextSchedules();
        return ResponseEntity.ok(myNextSchedules);
    }

}
