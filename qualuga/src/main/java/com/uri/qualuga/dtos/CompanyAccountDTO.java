package com.uri.qualuga.dtos;

import com.uri.qualuga.entities.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class CompanyAccountDTO {

    private Long companyId;

    @NotEmpty(message = "O nome deve ser informado.")
    @Length(max = 255)
    private String name;

    @NotEmpty(message = "O email deve ser informado.")
    @Email(message = "Formato do email inválido.")
    @Length(max = 255)
    private String email;

    private String password = "";

    @NotNull(message = "O endereço deve ser informado.")
    private AddressDTO address;

    public Company toEntity() {
        return Company.builder()
                .name(name)
                .email(email)
                .password(password)
                .address(address.toEntity()).build();
    }

}
