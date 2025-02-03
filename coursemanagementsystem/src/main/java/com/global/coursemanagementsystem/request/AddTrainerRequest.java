package com.global.coursemanagementsystem.request;

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
    private String email;
    private String expertise;
}
