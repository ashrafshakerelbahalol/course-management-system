package com.global.coursemanagementsystem.request;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.entity.TrainingSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEnrollmentRequest {
    private TrainingSession trainingSession;
    private Trainee trainee;
}
