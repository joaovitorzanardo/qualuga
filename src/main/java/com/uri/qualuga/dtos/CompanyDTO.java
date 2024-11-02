package com.uri.qualuga.dtos;

import com.uri.qualuga.dtos.response.CourtResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyDTO {

    private Long id;
    private String name;
    private AddressDTO address;
    private List<CourtResponse> courts;

}
