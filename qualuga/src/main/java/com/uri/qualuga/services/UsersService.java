package com.uri.qualuga.services;

import com.uri.qualuga.dtos.UserAccountDTO;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.exceptions.UserNotFoundException;
import com.uri.qualuga.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Users getLoggedUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usersRepository.findById(Long.valueOf(jwt.getSubject())).orElseThrow(UserNotFoundException::new);
    }

    public Users updateUser(UserAccountDTO userAccountDTO) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersRepository.findById(Long.valueOf(jwt.getSubject())).orElseThrow(UserNotFoundException::new);

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
