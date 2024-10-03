package com.uri.qualuga.entities;

import enums.Sports;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "court")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Court {

    @Id
    @Column(name = "court_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_sequence")
    @SequenceGenerator(name = "court_sequence",
            sequenceName = "court_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long courtId;

    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    private Sports sports;
}
