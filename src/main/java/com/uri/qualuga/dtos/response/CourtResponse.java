package com.uri.qualuga.dtos.response;

import com.uri.qualuga.dtos.CompanyDTO;
import com.uri.qualuga.dtos.CourtImageDTO;
import com.uri.qualuga.dtos.SportDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CourtResponse {

    private Long courtId;
    private Integer number;
    private CompanyDTO company;
    private List<CourtImageDTO> images;
    private List<SportDTO> sports;
}
