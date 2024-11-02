package com.uri.qualuga.controllers.account;

import com.uri.qualuga.dtos.CompanyDTO;
import com.uri.qualuga.dtos.ScheduleDTO;
import com.uri.qualuga.dtos.response.SucessResponse;
import com.uri.qualuga.dtos.account.UserAccountDTO;
import com.uri.qualuga.entities.Favorite;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.services.FavoriteService;
import com.uri.qualuga.services.ScheduleService;
import com.uri.qualuga.services.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user/myAccount")
public class UserController {

    @Autowired
    UsersService usersService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    FavoriteService favoriteService;

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
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<ScheduleDTO>> getMyAgenda() {
        List<ScheduleDTO> myNextSchedules = scheduleService.getMyNextSchedules();
        return ResponseEntity.ok(myNextSchedules);
    }

    @GetMapping(path = "/history")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<ScheduleDTO>> getMyHistory() {
        List<ScheduleDTO> myPreviousSchedules = scheduleService.getMyPreviousSchedules();
        return ResponseEntity.ok(myPreviousSchedules);
    }

    @PostMapping(path = "/favorites")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> addCompanyToFavorites(@RequestParam Long companyId) {
        Favorite favorite = favoriteService.addCompanyToFavorites(companyId);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(favorite.getFavoriteId())
                .message("Estabelecimento adicionado aos favoritos!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }

    @DeleteMapping(path = "/favorites")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> removeCompanyFromFavorites(@RequestParam Long companyId) {
        Favorite favorite = favoriteService.removeCompanyFromFavorites(companyId);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(favorite.getFavoriteId())
                .message("Estabelecimento removido dos favoritos!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.ok(sucessResponse);
    }

    @GetMapping(path = "/favorites")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<CompanyDTO>> getFavoriteCompanies() {
        List<CompanyDTO> favoriteCompanies = favoriteService.getFavoriteCompanies();
        return ResponseEntity.ok(favoriteCompanies);
    }

}
