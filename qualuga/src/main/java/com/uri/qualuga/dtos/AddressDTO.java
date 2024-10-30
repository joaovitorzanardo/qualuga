package com.uri.qualuga.dtos;

import com.uri.qualuga.entities.Address;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class AddressDTO {

    private Long addressId;

    @NotEmpty(message = "O cep deve ser informado.")
    @Length(min = 8, max = 8, message = "O cep deve ter oito caracteres.")
    private String cep;

    @NotEmpty(message = "A rua deve ser informada.")
    private String street;

    @NotNull(message = "O número deve ser informado.")
    private Integer number;

    @NotEmpty(message = "O bairro deve ser informado.")
    private String district;

    @NotEmpty(message = "A cidade deve ser informada.")
    private String city;

    @NotEmpty(message = "O estado deve ser informado.")
    @Length(min = 2, max = 2, message = "A UF deve ter dois caracteres.")
    private String state;

    public Address toEntity() {
        return Address.builder()
                .cep(cep)
                .street(street)
                .number(number)
                .district(district)
                .city(city)
                .state(state).build();
    }

}
