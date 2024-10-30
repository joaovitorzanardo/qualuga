package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.SucessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/account/favorites")
public class FavoriteController {

    @PostMapping
    public ResponseEntity<SucessResponse> addFavorite(@RequestParam Long companyId) {

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(companyId)
                .message("Estabelecimento adicionado aos favoritos!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }


}
