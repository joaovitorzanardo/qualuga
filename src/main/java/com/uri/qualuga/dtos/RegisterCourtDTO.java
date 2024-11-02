package com.uri.qualuga.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RegisterCourtDTO {

    @NotNull(message = "O número da quadra deve ser informado.")
    private Integer number;

    private List<CourtImageDTO> images;

    @NotNull(message = "Os esportes da quadra devem ser informados.")
    private List<SportDTO> sports;

}
