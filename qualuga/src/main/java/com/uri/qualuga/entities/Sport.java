package com.uri.qualuga.entities;

import com.uri.qualuga.dtos.SportDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "sport")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Sport {

    @Id
    @Column(name = "sport_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sport_sequence")
    @SequenceGenerator(name = "sport_sequence",
            sequenceName = "sport_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long sportId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "sports")
    private List<Court> court;

    public SportDTO toDTO() {
        return SportDTO.builder()
                .sportId(sportId)
                .name(name).build();
    }

}
