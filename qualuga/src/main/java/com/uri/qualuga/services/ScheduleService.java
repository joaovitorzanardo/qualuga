package com.uri.qualuga.services;

import com.uri.qualuga.dtos.AvailableSchedulesDTO;
import com.uri.qualuga.dtos.ScheduleDTO;
import com.uri.qualuga.entities.Court;
import com.uri.qualuga.entities.Schedule;
import com.uri.qualuga.exceptions.CourtNotFoundException;
import com.uri.qualuga.exceptions.InvalidScheduleException;
import com.uri.qualuga.repositories.CourtRepository;
import com.uri.qualuga.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CourtRepository courtRepository;
    @Autowired
    private CourtService courtService;

    public void addSchedules(ScheduleDTO scheduleDTO) {
        if (scheduleDTO.getStartTime().isAfter(scheduleDTO.getEndTime())) {
            throw new InvalidScheduleException("O horário de início não pode ser maior que o horário de fim.");
        }

        if (scheduleDTO.getStartTime().plusMinutes(scheduleDTO.getIntervalInMinutes()).isAfter(scheduleDTO.getEndTime())) {
            throw new InvalidScheduleException("Não é possível criar um horário com os valores informados.");
        }

        for (LocalDate date : scheduleDTO.getDates()) {
            LocalTime currentTime = scheduleDTO.getStartTime();

            while(currentTime.isBefore(scheduleDTO.getEndTime())) {
                LocalTime endTime = currentTime.plusMinutes(scheduleDTO.getIntervalInMinutes());

                for (Long courtId : scheduleDTO.getCourtIds()) {
                    Court court = courtRepository.findById(courtId)
                            .orElseThrow(CourtNotFoundException::new);

                    Schedule schedule = Schedule.builder()
                            .court(court)
                            .date(date)
                            .startTime(currentTime)
                            .endTime(endTime)
                            .available(true).build();

                    scheduleRepository.save(schedule);
                }

                currentTime = endTime;
            }
        }
    }

    public List<AvailableSchedulesDTO> getAvailableCourtSchedulesByDate(Long courtId, LocalDate date) {
        Court court = courtRepository.findById(courtId)
                .orElseThrow(CourtNotFoundException::new);

        List<Schedule> schedules = scheduleRepository
                .findAllByCourtAndDate(court, date);

        return schedules.stream()
                .filter(Schedule::isAvailable)
                .map(Schedule::toDTO)
                .toList();
    }

}
