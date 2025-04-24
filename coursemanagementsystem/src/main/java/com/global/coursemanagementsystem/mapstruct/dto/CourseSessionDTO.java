package com.global.coursemanagementsystem.mapstruct.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSessionDTO {
    private CourseDTO courseDTO;
    private List<TrainingSessionDTO> sessions;
}
