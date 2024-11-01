package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.CourtDTO;
import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.entities.Court;
import com.uri.qualuga.services.CourtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "")
public class CourtController {

    @Autowired
    private CourtService courtService;

    @PostMapping(path = "/company/courts")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> saveCourt(@Valid @RequestBody CourtDTO courtDTO) {
        Court court = courtService.saveCourt(courtDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(court.getCourtId())
                .message("Quadra criada com sucesso!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }

    @PutMapping(path = "/company/courts")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> updateCourt(@Valid @RequestBody CourtDTO courtDTO) {
        Court court = courtService.updateCourt(courtDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(court.getCourtId())
                .message("Quadra atualizada com sucesso!")
                .httpStatus(HttpStatus.OK).build();

        return ResponseEntity.status(HttpStatus.OK).body(sucessResponse);
    }

    @DeleteMapping(path = "/company/{companyId}/courts/{courtId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> deleteCourt(@PathVariable Long companyId, @PathVariable Long courtId) {
        Court court = courtService.deleteCourt(companyId, courtId);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(court.getCourtId())
                .message("Quadra excluída com sucesso!")
                .httpStatus(HttpStatus.OK).build();

        return ResponseEntity.status(HttpStatus.OK).body(sucessResponse);
    }

    @GetMapping(path = "/company/{companyId}/courts/{courtId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<CourtDTO> getCompanyCourtById(@PathVariable Long companyId, @PathVariable Long courtId) {
        return ResponseEntity.ok(courtService.getCompanyCourtById(companyId, courtId));
    }

    @GetMapping(path = "/company/{companyId}/courts")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<CourtDTO>> getCompanyCourts(@PathVariable Long companyId) {
        return ResponseEntity.ok(courtService.getCompanyCourts(companyId));
    }


}
