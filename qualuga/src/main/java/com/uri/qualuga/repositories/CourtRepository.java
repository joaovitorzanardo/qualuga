package com.uri.qualuga.repositories;

import com.uri.qualuga.entities.Company;
import com.uri.qualuga.entities.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

    Optional<Court> findCourtByCompanyAndCourtId(Company company, Long courtId);
    Optional<Court> findCourtByCompanyAndNumber(Company company, Integer number);

}
