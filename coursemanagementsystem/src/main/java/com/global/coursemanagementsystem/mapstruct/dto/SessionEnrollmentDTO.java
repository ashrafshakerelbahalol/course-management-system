package com.global.coursemanagementsystem.mapstruct.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionEnrollmentDTO {
    private TrainingSessionDTO trainingSessionDTO;
    private List <EnrollmentDTO> enrollmentDtos;
    
   
    
}
