package com.uri.qualuga.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class RegisterSchedulesDTO {

    @NotNull(message = "Pelo menos uma data deve ser informada.")
    private List<LocalDate> dates;

    @NotNull(message = "Pelo menos uma quadra deve ser informada.")
    private List<Long> courtIds;

    @NotNull(message = "O horário de início deve ser informado.")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @NotNull(message = "O horário de fim deve ser informado.")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @NotNull(message = "O tempo de intervalo entre os horários deve ser informado.")
    private Integer intervalInMinutes;

}
