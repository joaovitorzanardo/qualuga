package com.uri.qualuga.services.auth;

import com.uri.qualuga.entities.Account;

public class AuthService {

    private final Auth auth;

    public AuthService(Auth auth) {
        this.auth = auth;
    }

    public Long register(Account account) {
        return auth.register(account);
    }

    public boolean isEmailValid(String email) {
        return auth.isEmailValid(email);
    }

}
