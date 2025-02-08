package com.global.coursemanagementsystem.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTraineeRequest {
    private String firstName;
    private String lastName;   
    private String email;
    private String department;
    private String role;

}
