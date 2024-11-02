package com.uri.qualuga.controllers.account.company;

import com.uri.qualuga.dtos.account.CompanyAccountDTO;
import com.uri.qualuga.dtos.response.SucessResponse;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.services.CompanyAccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/company/myAccount")
public class CompanyAccountController {

    @Autowired
    private CompanyAccountService companyAccountService;

    @PutMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> updateCompanyAccount(@Valid @RequestBody CompanyAccountDTO companyAccountDTO) {
        Company company = companyAccountService.updateCompany(companyAccountDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(companyAccountDTO.getCompanyId())
                .message("Dados da empresa atualizados com sucesso!")
                .httpStatus(HttpStatus.OK).build();

        return ResponseEntity.ok(sucessResponse);
    }

    @GetMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<CompanyAccountDTO> getCompanyAccount() {
        CompanyAccountDTO companyAccountDTO = companyAccountService.getLoggedCompanyAccount();
        return ResponseEntity.ok(companyAccountDTO);
    }

}
