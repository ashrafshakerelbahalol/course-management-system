package com.global.coursemanagementsystem.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCourseRequest {
    private Integer courseId;
    private String courseName;
    private String description;
    private LocalDate duration;
}
