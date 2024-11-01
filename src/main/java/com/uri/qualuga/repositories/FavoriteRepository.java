package com.uri.qualuga.repositories;

import com.uri.qualuga.entities.Company;
import com.uri.qualuga.entities.Favorite;
import com.uri.qualuga.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByUserAndCompany(Users user, Company company);
    List<Favorite> findAllByUser(Users user);

}
