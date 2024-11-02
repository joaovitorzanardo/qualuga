package com.uri.qualuga.controllers;

import com.uri.qualuga.dtos.AvailableSchedulesDTO;
import com.uri.qualuga.dtos.response.SucessResponse;
import com.uri.qualuga.entities.Schedule;
import com.uri.qualuga.services.ScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping(path = "/{scheduleId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> appointSchedule(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleService.appointSchedule(scheduleId);

        SucessResponse sucessResponse = SucessResponse.builder()
                .message("Quadra " + schedule.getCourt().getNumber() + " reservada com sucesso!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }

    @GetMapping(path = "/courts/{courtId}")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<AvailableSchedulesDTO>> getAvailableCourtSchedulesByDate(@PathVariable Long courtId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(scheduleService.getAvailableCourtSchedulesByDate(courtId, date));
    }


}
