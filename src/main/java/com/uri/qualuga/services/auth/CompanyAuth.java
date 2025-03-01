package com.uri.qualuga.services.auth;

import com.uri.qualuga.dtos.request.LoginRequest;
import com.uri.qualuga.dtos.response.LoginResponse;
import com.uri.qualuga.entities.Account;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.exceptions.BadCredentialsException;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.repositories.CompanyRepository;
import com.uri.qualuga.repositories.UsersRepository;
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
    private UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Override
    public Long register(Account account) {
        if (!isEmailValid(account.getEmail())) {
            throw new EmailAlreadyExistsException(account.getEmail());
        }

        Company company = (Company) account;
        company.setPassword(passwordEncoder.encode(account.getPassword()));

        return companyRepository.save(company).getId();
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
        return companyRepository.findCompanyByEmail(email).isEmpty()
                && usersRepository.findUsersByEmail(email).isEmpty();
    }
}
