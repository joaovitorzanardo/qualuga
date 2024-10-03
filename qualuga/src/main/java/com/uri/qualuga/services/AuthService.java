package com.uri.qualuga.services;

import com.uri.qualuga.dtos.UsersDTO;
import com.uri.qualuga.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;

    public Integer register(UsersDTO usersDTO) {

    }

}
