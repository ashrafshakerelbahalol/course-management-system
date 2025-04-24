package com.global.coursemanagementsystem.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTrainerRequest {
    private Integer trainerId;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String expertise;
}
