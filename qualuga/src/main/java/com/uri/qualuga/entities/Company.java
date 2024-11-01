package com.uri.qualuga.entities;

import com.uri.qualuga.dtos.CompanyDTO;
import com.uri.qualuga.dtos.CourtDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Company implements Account {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_sequence")
    @SequenceGenerator(name = "company_sequence",
            sequenceName = "company_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Court> courts;

    public CompanyDTO toDTO() {
        List<CourtDTO> courtDTOS = new ArrayList<>();

        for (Court court : getCourts()) {
            courtDTOS.add(court.toDTO());
        }

        return CompanyDTO.builder()
                .id(id)
                .name(name)
                .address(address.toDTO())
                .courts(courtDTOS).build();
    }


}
