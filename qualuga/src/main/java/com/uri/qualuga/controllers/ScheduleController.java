package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.AvailableSchedulesDTO;
import com.uri.qualuga.dtos.ScheduleDTO;
import com.uri.qualuga.dtos.SucessResponse;
import com.uri.qualuga.services.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<SucessResponse> addSchedules(@Valid @RequestBody ScheduleDTO schedulesDTO) {
        scheduleService.addSchedules(schedulesDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .message("Horários cadastrados com sucesso!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }

    @GetMapping(path = "/available/{courtId}/{date}")
    public ResponseEntity<List<AvailableSchedulesDTO>> getAvailableCourtSchedulesByDate(@PathVariable Long courtId, @PathVariable LocalDate date) {
        return ResponseEntity.ok(scheduleService.getAvailableCourtSchedulesByDate(courtId, date));
    }


}
