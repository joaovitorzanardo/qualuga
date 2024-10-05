package com.uri.qualuga.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uri.qualuga.entities.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class UserDTO {

    @NotNull
    private Long userId;

    @NotEmpty
    @Length(max = 255)
    private String name;

    @NotEmpty
    @Email
    @Length(max = 255)
    private String email;

    @Length(max = 100)
    private String password = "";

    public Users toEntity() {
        return Users.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .password(password).build();
    }

}
