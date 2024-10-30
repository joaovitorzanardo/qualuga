package com.uri.qualuga.services.auth;

import com.uri.qualuga.entities.Account;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("beanUserAuth")
public class UserAuth implements Auth {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Long register(Account account) {
        if (!isEmailValid(account.getEmail())) {
            throw new EmailAlreadyExistsException(account.getEmail());
        }

        return usersRepository.save((Users) account).getUserId();
    }

    @Override
    public boolean isEmailValid(String email) {
        return usersRepository.findUsersByEmail(email).isEmpty();
    }
}
