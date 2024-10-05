package com.uri.qualuga.services;

import com.uri.qualuga.dtos.RegisterDTO;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsersRepository usersRepository;

    public Users register(RegisterDTO registerDTO) {
        Optional<Users> user = usersRepository.findUsersByEmail(registerDTO.getEmail());

        if (user.isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        return usersRepository.save(registerDTO.toUsersEntity());
    }

}
