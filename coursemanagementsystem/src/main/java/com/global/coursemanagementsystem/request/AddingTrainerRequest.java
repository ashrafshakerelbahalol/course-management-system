package com.global.coursemanagementsystem.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddingTrainerRequest {
    private Integer trainerId;
    private String firstName;
    private String lastName;
    private String email;
    private String expertise;
}
