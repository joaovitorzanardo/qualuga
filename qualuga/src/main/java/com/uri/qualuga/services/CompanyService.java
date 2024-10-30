package com.uri.qualuga.services;

import com.uri.qualuga.dtos.CompanyAccountDTO;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.exceptions.CompanyNotFoundException;
import com.uri.qualuga.exceptions.EmailAlreadyExistsException;
import com.uri.qualuga.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company getCompany(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
    }

    public Company updateCompany(CompanyAccountDTO companyAccountDTO) {
        Company company = companyRepository.findById(companyAccountDTO.getCompanyId()).orElseThrow(CompanyNotFoundException::new);

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
