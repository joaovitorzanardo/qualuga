package com.uri.qualuga.entities;

import com.uri.qualuga.dtos.AddressDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence",
            sequenceName = "address_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long addressId;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    public AddressDTO toDTO() {
        return AddressDTO.builder()
                .addressId(addressId)
                .cep(cep)
                .street(street)
                .number(number)
                .district(district)
                .city(city)
                .state(state)
                .build();
    }

}
