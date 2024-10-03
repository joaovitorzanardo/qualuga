package com.uri.qualuga.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Schedule {

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_sequence")
    @SequenceGenerator(name = "schedule_sequence",
            sequenceName = "schedule_sequence",
            initialValue = 1,
            allocationSize = 1)
    private Long scheduleId;




}
