package com.global.coursemanagementsystem.mapstruct.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private String description;
    private LocalDateTime  duration; 
}
