package com.uri.qualuga.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AvailableSchedulesDTO {

    private Long scheduleId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

}
