package com.uri.qualuga.services;

import com.uri.qualuga.dtos.AvailableSchedulesDTO;
import com.uri.qualuga.dtos.RegisterSchedulesDTO;
import com.uri.qualuga.dtos.ScheduleDTO;
import com.uri.qualuga.entities.Agenda;
import com.uri.qualuga.entities.Court;
import com.uri.qualuga.entities.Schedule;
import com.uri.qualuga.entities.Users;
import com.uri.qualuga.exceptions.*;
import com.uri.qualuga.repositories.AgendaRepository;
import com.uri.qualuga.repositories.CourtRepository;
import com.uri.qualuga.repositories.ScheduleRepository;
import com.uri.qualuga.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
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
    private UsersRepository usersRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    public void addSchedules(RegisterSchedulesDTO scheduleDTO) {
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
        if (date.isBefore(LocalDate.now())) {
            throw new InvalidDateException();
        }

        Court court = courtRepository.findById(courtId)
                .orElseThrow(CourtNotFoundException::new);

        List<Schedule> schedules = scheduleRepository
                .findAllByCourtAndDate(court, date);

        return schedules.stream()
                .filter(schedule -> schedule.isAvailable() && (date.isEqual(LocalDate.now()) ? schedule.getStartTime().isAfter(LocalTime.now()) : true))
                .map(Schedule::toAvailableSchedulesDTO)
                .toList();
    }

    public Schedule appointSchedule(Long scheduleId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users loggedUser = usersRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(UserNotFoundException::new);

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(ScheduleNotAvailableException::new);

        if (!schedule.isAvailable()) {
            throw new ScheduleNotAvailableException();
        }

        Agenda agenda = Agenda.builder()
                .user(loggedUser)
                .schedule(schedule).build();

        agendaRepository.save(agenda);

        schedule.setAvailable(false);
        scheduleRepository.save(schedule);

        return schedule;
    }

    public List<ScheduleDTO> getMyNextSchedules() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users loggedUser = usersRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(UserNotFoundException::new);

        List<Agenda> myAgenda = agendaRepository.findAllByUser(loggedUser);

        return myAgenda.stream()
                .filter((agenda) -> agenda.getSchedule().getDate().isAfter(LocalDate.now())
                        || (agenda.getSchedule().getDate().isEqual(LocalDate.now()) && agenda.getSchedule().getStartTime().isAfter(LocalTime.now())))
                .map((agenda -> agenda.getSchedule().toScheduleDTO()))
                .toList();
    }

    public List<ScheduleDTO> getMyPreviousSchedules() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users loggedUser = usersRepository.findById(Long.valueOf(jwt.getSubject()))
                .orElseThrow(UserNotFoundException::new);

        List<Agenda> myAgenda = agendaRepository.findAllByUser(loggedUser);

        return myAgenda.stream()
                .filter((agenda) -> agenda.getSchedule().getDate().isBefore(LocalDate.now())
                        || (agenda.getSchedule().getDate().isEqual(LocalDate.now()) && agenda.getSchedule().getStartTime().isBefore(LocalTime.now())))
                .map((agenda -> agenda.getSchedule().toScheduleDTO()))
                .toList();
    }

}
