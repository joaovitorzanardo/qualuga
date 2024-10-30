package com.uri.qualuga.services.auth;

import com.uri.qualuga.entities.Account;
import org.springframework.stereotype.Service;

public interface Auth {

    Long register(Account account);
    boolean isEmailValid(String email);

}
