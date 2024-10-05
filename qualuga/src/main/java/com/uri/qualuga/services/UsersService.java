package com.uri.qualuga.services;

import com.uri.qualuga.dtos.UserDTO;
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

    public Users updateUser(UserDTO userDTO) {
        Users user = usersRepository.findById(userDTO.getUserId()).orElseThrow(UserNotFoundException::new);

        if (!user.getEmail().equals(userDTO.getEmail())) {
            Optional<Users> userEmail = usersRepository.findUsersByEmail(userDTO.getEmail());

            if (userEmail.isPresent()) throw new EmailAlreadyExistsException();
        }

        user.setName(user.getName());
        user.setEmail(userDTO.getEmail());

        if (!userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword());
        }

        return usersRepository.save(user);
    }

}
