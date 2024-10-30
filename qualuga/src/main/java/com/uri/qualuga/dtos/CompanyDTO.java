package com.uri.qualuga.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyDTO {

    private Long id;
    private String name;
    private AddressDTO address;
    private List<CourtDTO> courts;

}
