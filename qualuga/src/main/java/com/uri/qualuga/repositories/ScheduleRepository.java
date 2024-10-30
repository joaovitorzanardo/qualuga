package com.uri.qualuga.repositories;

import com.uri.qualuga.entities.Court;
import com.uri.qualuga.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByCourtAndDate(Court court, LocalDate date);

}
