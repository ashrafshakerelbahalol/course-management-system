package com.global.coursemanagementsystem.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDTO {
    private Long traineeId;
    private String firstName;
    private String lastName;   
    private String email;
    private String department;
    private String role;

}
