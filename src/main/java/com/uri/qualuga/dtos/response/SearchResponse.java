package com.uri.qualuga.dtos.response;

import com.uri.qualuga.dtos.CompanyDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResponse {

    private List<CompanyDTO> companies;
    private List<CourtResponse> courts;

}
