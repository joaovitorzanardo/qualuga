package com.uri.qualuga.services;

import com.uri.qualuga.dtos.*;
import com.uri.qualuga.dtos.request.SearchRequest;
import com.uri.qualuga.dtos.response.CourtResponse;
import com.uri.qualuga.dtos.response.SearchResponse;
import com.uri.qualuga.entities.Company;
import com.uri.qualuga.entities.Schedule;
import com.uri.qualuga.exceptions.InvalidDateException;
import com.uri.qualuga.repositories.CompanyRepository;
import com.uri.qualuga.repositories.CourtRepository;
import com.uri.qualuga.repositories.ScheduleRepository;
import com.uri.qualuga.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CourtRepository courtRepository;

    @Autowired
    SportRepository sportRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public SearchResponse search(SearchRequest searchRequest) {
        List<CompanyDTO> companiesFound = new ArrayList<>();
        List<CourtResponse> courtsFound = new ArrayList<>();

        if (!searchRequest.getSearchText().trim().isEmpty()) {
            companiesFound = getCompanyByName(searchRequest.getSearchText().trim());
        }

        if (searchRequest.getDate() != null && searchRequest.getTime() == null) {
            courtsFound = getAvailableCourtsByDate(searchRequest.getDate());
        }

        if (searchRequest.getDate() != null && searchRequest.getTime() != null) {
            courtsFound = getAvailableCourtsByDateAndTime(searchRequest.getDate(), searchRequest.getTime());
        }

        if (!searchRequest.getSportIds().isEmpty()) {
            courtsFound = courtsFound.stream()
                    .filter((court) -> {
                        boolean contains = false;
                        for (Long ids : searchRequest.getSportIds()){
                            contains = contains(court.getSports(), ids);
                        }

                        return contains;
                    }).toList();
        }

        return SearchResponse.builder()
                .companies(companiesFound)
                .courts(courtsFound).build();
    }

    private boolean contains(List<SportDTO> sports, Long sportId) {
        boolean contains = false;
        for (SportDTO sport : sports) {
            if (sport.getSportId().equals(sportId)) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    private List<CompanyDTO> getCompanyByName(String name) {
        List<CompanyDTO> companiesDTOs = new ArrayList<>();

        List<Company> companies = companyRepository.findAllByNameContainingIgnoreCase(name);

        for (Company company : companies) {
            companiesDTOs.add(company.toCompanyDTO());
        }

        return companiesDTOs;
    }

    private List<CourtResponse> getAvailableCourtsByDate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new InvalidDateException();
        }

        List<Schedule> schedules = scheduleRepository.findAllByDate(date);

        return schedules.stream()
                .filter((schedule) -> {
                    if (date.equals(LocalDate.now())) {
                        return schedule.isAvailable() && schedule.getStartTime().isAfter(LocalTime.now());
                    }

                    return schedule.isAvailable();
                })
                .map((schedule) -> schedule.getCourt().toCourtResponse()).toList();
    }

    private List<CourtResponse> getAvailableCourtsByDateAndTime(LocalDate date, LocalTime time) {
        if (date.isBefore(LocalDate.now()) || (date.isEqual(LocalDate.now()) && time.isBefore(LocalTime.now()))) {
            throw new InvalidDateException();
        }

        List<Schedule> schedules = scheduleRepository.findAllByDateAndStartTime(date, time);

        return schedules.stream()
                .filter(Schedule::isAvailable)
                .map((schedule) -> schedule.getCourt().toCourtResponse()).toList();
    }


}
