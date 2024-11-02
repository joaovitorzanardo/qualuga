package com.uri.qualuga.repositories;

import com.uri.qualuga.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findCompanyByEmail(String email);

    List<Company> findAllByNameContainingIgnoreCase(String name);

}
