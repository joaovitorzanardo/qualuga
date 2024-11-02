package com.uri.qualuga.config;

import com.uri.qualuga.entities.Account;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.repositories.CompanyRepository;
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
import java.util.Optional;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();

        Optional<Users> user = usersRepository.findById(Long.valueOf(username));
        Optional<Company> company = companyRepository.findById(Long.valueOf(username));

        if (user.isEmpty() && company.isEmpty()) {
            throw new UsernameNotFoundException("Conta não encontrada!");
        }

        Account account = null;

        if (user.isPresent()) {
            account = user.get();
        } else {
            account = company.get();
        }

        String email = account.getEmail();
        String password = account.getPassword();

        return new User(email, password, authorities);
    }

}
