package com.global.coursemanagementsystem.mapstruct.dto;

import com.global.coursemanagementsystem.entity.Enrollment;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentDTO { 
    private Long assessmentId;
    private Enrollment enrollment;
    private byte score;
    private String feedback;
}

