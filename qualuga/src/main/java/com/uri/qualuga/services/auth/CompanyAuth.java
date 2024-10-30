package com.uri.qualuga.services.auth;

import com.uri.qualuga.entities.Account;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("beanCompanyAuth")
public class CompanyAuth implements Auth {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Long register(Account account) {
        if (!isEmailValid(account.getEmail())) {
            throw new EmailAlreadyExistsException(account.getEmail());
        }

        return companyRepository.save((Company) account).getCompanyId();
    }

    @Override
    public boolean isEmailValid(String email) {
        return companyRepository.findCompanyByEmail(email).isEmpty();
    }
}
