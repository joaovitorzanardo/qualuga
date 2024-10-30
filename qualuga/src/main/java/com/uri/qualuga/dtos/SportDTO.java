package com.uri.qualuga.dtos;

import com.uri.qualuga.entities.Sport;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SportDTO {

    private Long sportId;

    @NotEmpty(message = "O nome do esporte deve ser informado.")
    private String name;

    public Sport toEntity() {
        return Sport.builder()
                .name(name).build();
    }

}
