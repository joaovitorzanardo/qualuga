package com.uri.qualuga.controllers.company;

import com.uri.qualuga.dtos.CompanyDTO;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(path = "/{companyId}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long companyId) {
        Company company = companyService.getCompany(companyId);
        return ResponseEntity.ok(company.toDTO());
    }

}
