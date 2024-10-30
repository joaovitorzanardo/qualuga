package com.uri.qualuga.controllers.auth;

import com.uri.qualuga.dtos.CheckEmailDTO;
import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.dtos.UserAccountDTO;
import com.uri.qualuga.services.auth.AuthService;
import com.uri.qualuga.services.auth.UserAuth;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserAuthController extends AuthController {

    @Autowired
    @Qualifier("beanUserAuth")
    private UserAuth userAuth;

    private AuthService authService;

    @PostConstruct
    public void initAuthService() {
        this.authService = new AuthService(userAuth);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<SucessResponse> register(@Valid @RequestBody UserAccountDTO userRegisterDTO) {
        Long createdUserId = authService.register(userRegisterDTO.toEntity());

        SucessResponse sucessResponse = SucessResponse.builder()
                .id(createdUserId)
                .message("Usuário criado com sucesso!")
                .httpStatus(HttpStatus.CREATED).build();

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
