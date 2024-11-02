package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.SportDTO;
import com.uri.qualuga.dtos.response.SucessResponse;
import com.uri.qualuga.entities.Sport;
import com.uri.qualuga.services.SportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/sports")
public class SportController {

    @Autowired
    private SportService sportService;

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> saveSport(SportDTO sportDTO) {
        Sport sport = sportService.saveSport(sportDTO.toEntity());

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(sport.getSportId())
                .message("Esporte criado com sucesso!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }

    @GetMapping
    @SecurityRequirement(name = "Authorization")
    public List<SportDTO> getSports() {
        return sportService.getSports();
    }


}
