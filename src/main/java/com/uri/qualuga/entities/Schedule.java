package com.uri.qualuga.entities;

import com.uri.qualuga.dtos.AvailableSchedulesDTO;
import com.uri.qualuga.dtos.ScheduleDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id", nullable = false)
    private Court court;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "available", nullable = false)
    private boolean available = true;

    public AvailableSchedulesDTO toAvailableSchedulesDTO() {
        return AvailableSchedulesDTO.builder()
                .scheduleId(scheduleId)
                .date(date)
                .startTime(startTime)
                .endTime(endTime).build();
    }

    public ScheduleDTO toScheduleDTO() {
        return ScheduleDTO.builder()
                .court(court.toDTO())
                .date(date)
                .startTime(startTime)
                .endTime(endTime).build();
    }

}
