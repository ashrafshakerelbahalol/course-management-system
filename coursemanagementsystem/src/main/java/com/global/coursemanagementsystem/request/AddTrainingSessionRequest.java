package com.global.coursemanagementsystem.request;

import java.time.LocalTime;

import com.global.coursemanagementsystem.entity.Course;
import com.global.coursemanagementsystem.entity.Trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTrainingSessionRequest {
    private Long session_id;
    private Course course;
    private Trainer trainer;
    private LocalTime startTime;
    private LocalTime endTime;
}
