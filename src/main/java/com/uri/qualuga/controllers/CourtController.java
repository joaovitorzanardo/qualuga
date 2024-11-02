package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.RegisterCourtDTO;
import com.uri.qualuga.dtos.response.CourtResponse;
import com.uri.qualuga.services.CourtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/courts")
public class CourtController {

    @Autowired
    private CourtService courtService;

    @GetMapping(path = "/companies/{companyId}/court/{courtId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<CourtResponse> getCompanyCourtById(@PathVariable Long companyId, @PathVariable Long courtId) {
        return ResponseEntity.ok(courtService.getCompanyCourtById(companyId, courtId));
    }

    @GetMapping(path = "/companies/{companyId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<CourtResponse>> getCompanyCourts(@PathVariable Long companyId) {
        return ResponseEntity.ok(courtService.getCompanyCourts(companyId));
    }

}
