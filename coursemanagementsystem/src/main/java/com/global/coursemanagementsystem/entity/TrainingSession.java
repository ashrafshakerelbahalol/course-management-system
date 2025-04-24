package com.global.coursemanagementsystem.entity;


import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "training_session")
public class TrainingSession {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long sessionId;
    @ManyToOne
    @JoinColumn(name = "course_id") 
    private Course course;
    @ManyToOne
    @JoinColumn(name="trainer_id")
    private Trainer trainer;
    @Column(name="start_date")
    private LocalTime startTime;
    @Column(name="end_date")
    private LocalTime endTime;
}
