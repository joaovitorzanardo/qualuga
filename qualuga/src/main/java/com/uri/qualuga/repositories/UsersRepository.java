package com.uri.qualuga.repositories;

import com.uri.qualuga.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Long, Users> {
}
