package com.uri.qualuga.services;

import com.uri.qualuga.dtos.UserAccountDTO;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.exceptions.UserNotFoundException;
import com.uri.qualuga.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Users getUser(Long userId) {
        return usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public Users updateUser(UserAccountDTO userAccountDTO) {
        Users user = usersRepository.findById(userAccountDTO.getUserId()).orElseThrow(UserNotFoundException::new);

        if (!user.getEmail().equals(userAccountDTO.getEmail())) {
            Optional<Users> userEmail = usersRepository.findUsersByEmail(userAccountDTO.getEmail());

            if (userEmail.isPresent()) throw new EmailAlreadyExistsException(userAccountDTO.getEmail());
        }

        user.setName(userAccountDTO.getName());
        user.setEmail(userAccountDTO.getEmail());

        if (!userAccountDTO.getPassword().isEmpty()) {
            user.setPassword(userAccountDTO.getPassword());
        }

        return usersRepository.save(user);
    }

}
