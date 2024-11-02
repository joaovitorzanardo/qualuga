package com.uri.qualuga.services.auth;

import com.uri.qualuga.dtos.request.LoginRequest;
import com.uri.qualuga.dtos.response.LoginResponse;
import com.uri.qualuga.entities.Account;
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

@Component("beanUserAuth")
public class UserAuth implements Auth {

    @Autowired
    private UsersRepository usersRepository;

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

        Users user = (Users) account;
        user.setPassword(passwordEncoder.encode(account.getPassword()));

        return usersRepository.save(user).getId();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Users> userOptional = usersRepository
                .findUsersByEmail(loginRequest.email());

        if (userOptional.isEmpty() || !passwordEncoder.matches(loginRequest.password(), userOptional.get().getPassword())) {
            throw new BadCredentialsException();
        }

        Long expiresIn = 28800L;

        String jwtValue = tokenService.generateToken(userOptional.get(), expiresIn);

        return new LoginResponse(jwtValue, expiresIn);
    }

    @Override
    public boolean isEmailValid(String email) {
        return usersRepository.findUsersByEmail(email).isEmpty()
                && companyRepository.findCompanyByEmail(email).isEmpty();
    }
}
