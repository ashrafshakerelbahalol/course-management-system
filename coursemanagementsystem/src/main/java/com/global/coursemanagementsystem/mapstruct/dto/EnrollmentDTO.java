package com.global.coursemanagementsystem.mapstruct.dto;

import com.global.coursemanagementsystem.entity.TrainingSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
    private Long enrollmentId;
    private Long trainingSessionId;
    private String traineeName ;
}
