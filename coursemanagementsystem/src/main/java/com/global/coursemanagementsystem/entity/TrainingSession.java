package com.global.coursemanagementsystem.entity;


import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingSession {
    @Id
    private Long session_id;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Trainer trainer;
    @Column(name="start_date")
    private LocalTime startTime;
    @Column(name="end_date")
    private LocalTime endTime;
}
