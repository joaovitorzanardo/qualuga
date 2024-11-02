package com.uri.qualuga.services.auth;

import com.uri.qualuga.dtos.request.LoginRequest;
import com.uri.qualuga.dtos.response.LoginResponse;
import com.uri.qualuga.entities.Account;

public interface Auth {

    Long register(Account account);
    boolean isEmailValid(String email);
    LoginResponse login(LoginRequest loginRequest);

}
