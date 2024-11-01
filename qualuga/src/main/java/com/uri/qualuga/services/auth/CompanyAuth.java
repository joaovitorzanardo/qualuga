package com.uri.qualuga.services.auth;

import com.uri.qualuga.dtos.LoginRequest;
import com.uri.qualuga.dtos.LoginResponse;
import com.uri.qualuga.entities.Account;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.exceptions.BadCredentialsException;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.repositories.CompanyRepository;
import com.uri.qualuga.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("beanCompanyAuth")
public class CompanyAuth implements Auth {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Override
    public Long register(Account account) {
        if (!isEmailValid(account.getEmail())) {
            throw new EmailAlreadyExistsException(account.getEmail());
        }

        return companyRepository.save((Company) account).getId();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Company> companyOptional = companyRepository
                .findCompanyByEmail(loginRequest.email());

        if (companyOptional.isEmpty() || !passwordEncoder.matches(loginRequest.password(), companyOptional.get().getPassword())) {
            throw new BadCredentialsException();
        }

        Long expiresIn = 28800L;

        String jwtValue = tokenService.generateToken(companyOptional.get(), expiresIn);

        return new LoginResponse(jwtValue, expiresIn);
    }

    @Override
    public boolean isEmailValid(String email) {
        return companyRepository.findCompanyByEmail(email).isEmpty();
    }
}
