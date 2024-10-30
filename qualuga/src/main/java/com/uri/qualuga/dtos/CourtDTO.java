package com.uri.qualuga.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CourtDTO {

    private Long courtId;

    @NotNull(message = "O número da quadra deve ser informado.")
    private Integer number;

    @NotNull(message = "O id da empresa deve ser informado.")
    private Long companyId;

    private List<CourtImageDTO> images;

    @NotNull(message = "Os esportes da quadra devem ser informados.")
    private List<SportDTO> sports;

}
