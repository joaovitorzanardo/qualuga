package com.uri.qualuga.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourtImageDTO {

    private Long courtImageId;

    @NotEmpty(message = "A url da imagem deve ser informada.")
    private String url;

}
