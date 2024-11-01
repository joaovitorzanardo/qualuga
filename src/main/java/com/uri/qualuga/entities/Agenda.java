package com.uri.qualuga.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "agenda")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Agenda {

    @Id
    @Column(name = "agenda_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agenda_sequence")
    @SequenceGenerator(name = "agenda_sequence",
            sequenceName = "agenda_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long agendaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

}
