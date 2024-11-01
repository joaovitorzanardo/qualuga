package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.CompanyDTO;
import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.entities.Favorite;
import com.uri.qualuga.services.FavoriteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/account/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> addCompanyToFavorites(@RequestParam Long companyId) {
        Favorite favorite = favoriteService.addCompanyToFavorites(companyId);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(favorite.getFavoriteId())
                .message("Estabelecimento adicionado aos favoritos!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }

    @DeleteMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> removeCompanyFromFavorites(@RequestParam Long companyId) {
        Favorite favorite = favoriteService.removeCompanyFromFavorites(companyId);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(favorite.getFavoriteId())
                .message("Estabelecimento removido dos favoritos!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.ok(sucessResponse);
    }

    @GetMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<CompanyDTO>> getFavoriteCompanies() {
        List<CompanyDTO> favoriteCompanies = favoriteService.getFavoriteCompanies();
        return ResponseEntity.ok(favoriteCompanies);
    }


}
