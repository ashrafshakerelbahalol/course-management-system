package com.global.coursemanagementsystem.mapstruct.dto;

import java.time.LocalTime;

import com.global.coursemanagementsystem.entity.Course;
import com.global.coursemanagementsystem.entity.Trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingSessionDTO {
    private Long sessionId;
    private String courseName;
    private String trainerName;
    private LocalTime startTime;
    private LocalTime endTime;}
