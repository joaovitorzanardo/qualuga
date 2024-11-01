package com.uri.qualuga.controllers.company;

import com.uri.qualuga.dtos.CompanyAccountDTO;
import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.services.CompanyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/account")
public class CompanyAccountController extends CompanyController {

    @Autowired
    private CompanyService companyService;

    @PutMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> updateCompanyAccount(@Valid @RequestBody CompanyAccountDTO companyAccountDTO) {
        Company company = companyService.updateCompany(companyAccountDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(companyAccountDTO.getCompanyId())
                .message("Empresa atualizada com sucesso!")
                .httpStatus(HttpStatus.OK).build();

        return ResponseEntity.ok(sucessResponse);
    }

}
