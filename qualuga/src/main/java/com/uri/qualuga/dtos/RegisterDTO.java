package com.uri.qualuga.dtos;

import com.uri.qualuga.entities.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class RegisterDTO {

    @NotEmpty
    @Length(max = 255)
    private String name;

    @NotEmpty
    @Email
    @Length(max = 255)
    private String email;

    @NotEmpty
    @Length(max = 100)
    private String password;

    public Users toUsersEntity() {
        return Users.builder()
                .name(name)
                .email(email)
                .password(password).build();
    }

}
