package com.uri.qualuga.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckEmailResponse {

    private String email;
    private boolean isValid;

}
