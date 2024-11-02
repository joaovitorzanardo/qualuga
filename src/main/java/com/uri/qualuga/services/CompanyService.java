package com.uri.qualuga.services;

import com.uri.qualuga.dtos.CompanyDTO;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.exceptions.CompanyNotFoundException;
import com.uri.qualuga.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company getCompany(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
    }

    public List<CompanyDTO> getAllCompanies(Integer page, Integer perPage) {
        List<CompanyDTO> companies = new ArrayList<>();

        Page<Company> companyPage = companyRepository.findAll(PageRequest.of(page, perPage));

        for (Company company : companyPage.getContent()) {
            companies.add(company.toCompanyDTO());
        }

        return companies;
    }

}
