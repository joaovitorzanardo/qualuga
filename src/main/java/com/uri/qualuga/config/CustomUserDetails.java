package com.uri.qualuga.config;

import com.uri.qualuga.entities.Users;
import com.uri.qualuga.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Users user = usersRepository.findById(Long.valueOf(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        String email = user.getEmail();
        String password = user.getPassword();

        return new User(email, password, authorities);
    }

}
