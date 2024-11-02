package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.CompanyDTO;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.services.CompanyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(path = "/{companyId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long companyId) {
        Company company = companyService.getCompany(companyId);
        return ResponseEntity.ok(company.toCompanyDTO());
    }

    @GetMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<CompanyDTO>> getCompanies(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "per_page", required = false, defaultValue = "25") Integer perPage
    ) {
        List<CompanyDTO> companies = companyService.getAllCompanies(page, perPage);
        return ResponseEntity.ok(companies);
    }

}
