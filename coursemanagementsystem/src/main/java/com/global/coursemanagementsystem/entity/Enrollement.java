package com.global.coursemanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Enrollement {
    @Id
    private Long enrollementId;
    @ManyToOne
    private TrainingSession trainingSession;
    @ManyToOne
    private Trainee trainee;
    
}
