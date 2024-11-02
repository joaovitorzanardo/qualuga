package com.uri.qualuga.controllers.account.company;

import com.uri.qualuga.dtos.RegisterSchedulesDTO;
import com.uri.qualuga.dtos.response.SucessResponse;
import com.uri.qualuga.services.ScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/company/myAccount/schedules")
public class CompanyScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<SucessResponse> addSchedules(@Valid @RequestBody RegisterSchedulesDTO schedulesDTO) {
        scheduleService.addSchedules(schedulesDTO);

        SucessResponse sucessResponse = SucessResponse.builder()
                .message("Horários cadastrados com sucesso!")
                .httpStatus(HttpStatus.CREATED).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(sucessResponse);
    }

}
