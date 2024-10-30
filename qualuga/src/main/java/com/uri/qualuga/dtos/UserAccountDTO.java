package com.uri.qualuga.dtos;

import com.uri.qualuga.entities.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class UserAccountDTO {

    private Long userId;

    @NotEmpty(message = "O nome deve ser informado.")
    @Length(max = 255)
    private String name;

    @NotEmpty(message = "O email deve ser informado.")
    @Email(message = "Formato do email inválido.")
    @Length(max = 255)
    private String email;

    private String password = "";

    public Users toEntity() {
        return Users.builder()
                .name(name)
                .email(email)
                .password(password).build();
    }

}
