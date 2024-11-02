package com.uri.qualuga.controllers.account.company;

import com.uri.qualuga.dtos.RegisterCourtDTO;
import com.uri.qualuga.dtos.response.SucessResponse;
import com.uri.qualuga.entities.Court;
import com.uri.qualuga.services.CourtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/company/myAccount/courts")
public class CompanyCourtController {

    @Autowired
    private CourtService courtService;

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> saveCourt(@Valid @RequestBody RegisterCourtDTO courtDTO) {
        Court court = courtService.saveCourt(courtDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(court.getCourtId())
                .message("Quadra criada com sucesso!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }

    @PutMapping(path = "/{courtId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> updateCourt(@PathVariable Long courtId, @Valid @RequestBody RegisterCourtDTO courtDTO) {
        Court court = courtService.updateCourt(courtId, courtDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(court.getCourtId())
                .message("Quadra atualizada com sucesso!")
                .httpStatus(HttpStatus.OK).build();

        return ResponseEntity.status(HttpStatus.OK).body(sucessResponse);
    }

    @DeleteMapping(path = "/{courtId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> deleteCourt(@PathVariable Long courtId) {
        Court court = courtService.deleteCourt(courtId);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(court.getCourtId())
                .message("Quadra excluída com sucesso!")
                .httpStatus(HttpStatus.OK).build();

        return ResponseEntity.status(HttpStatus.OK).body(sucessResponse);
    }

}
