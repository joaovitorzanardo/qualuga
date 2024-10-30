package com.uri.qualuga.repositories;

import com.uri.qualuga.entities.CourtImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtImageRepository extends JpaRepository<CourtImage, Integer> {
}
