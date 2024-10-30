package com.uri.qualuga.controllers.auth;

import com.uri.qualuga.dtos.CheckEmailDTO;
import com.uri.qualuga.dtos.CompanyAccountDTO;
import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.services.auth.AuthService;
import com.uri.qualuga.services.auth.CompanyAuth;
import com.uri.qualuga.services.auth.UserAuth;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/company")
public class CompanyAuthController {

    @Autowired
    @Qualifier("beanCompanyAuth")
    private CompanyAuth companyAuth;

    private AuthService authService;

    @PostConstruct
    public void initAuthService() {
        this.authService = new AuthService(companyAuth);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<SucessResponse> register(@Valid @RequestBody CompanyAccountDTO registerCompanyDTO) {
        Long createdCompanyId = authService.register(registerCompanyDTO.toEntity());

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(createdCompanyId)
                .message("Empresa criada com sucesso!")
                .httpStatus(HttpStatus.CREATED)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }

    @GetMapping(path ="/checkEmail/{email}")
    public ResponseEntity<CheckEmailDTO> checkEmail(@PathVariable String email) {
        boolean isEmailValid = authService.isEmailValid(email);

        CheckEmailDTO checkEmailDTO = CheckEmailDTO.builder()
                .email(email)
                .isValid(isEmailValid)
                .build();

        return ResponseEntity.ok(checkEmailDTO);
    }

}
