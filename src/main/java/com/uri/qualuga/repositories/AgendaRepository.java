package com.uri.qualuga.repositories;

import com.uri.qualuga.entities.Agenda;
import com.uri.qualuga.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findAllByUser(Users user);

}
