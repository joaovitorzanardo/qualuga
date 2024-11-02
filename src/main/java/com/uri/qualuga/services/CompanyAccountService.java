package com.uri.qualuga.services;

import com.uri.qualuga.dtos.account.CompanyAccountDTO;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.exceptions.CompanyNotFoundException;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyAccountService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyAccountDTO getLoggedCompanyAccount() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Company company = companyRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(CompanyNotFoundException::new);

        return company.toCompanyAccountDTO();
    }

    public Company updateCompany(CompanyAccountDTO companyAccountDTO) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(CompanyNotFoundException::new);

        if (!companyAccountDTO.getEmail().equals(company.getEmail())) {
            Optional<Company> companyEmail = companyRepository.findCompanyByEmail(companyAccountDTO.getEmail());

            if (companyEmail.isPresent()) {
                throw new EmailAlreadyExistsException(companyAccountDTO.getEmail());
            }

            company.setEmail(companyAccountDTO.getEmail());
        }

        if (!companyAccountDTO.getPassword().isEmpty()) {
            company.setPassword(companyAccountDTO.getPassword());
        }

        company.setName(companyAccountDTO.getName());
        company.setAddress(companyAccountDTO.getAddress().toEntity());

        return companyRepository.save(company);
    }
}
