package com.uri.qualuga.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class SearchRequest {

    private String searchText;

    private List<Long> sportIds;

    private LocalDate date;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;

}
