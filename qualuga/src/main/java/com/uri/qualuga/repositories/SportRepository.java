package com.uri.qualuga.repositories;

import com.uri.qualuga.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportRepository extends JpaRepository<Sport, Long> {

    Optional<Sport> findSportByNameIgnoreCase(String name);

}
