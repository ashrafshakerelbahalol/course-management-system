package com.global.coursemanagementsystem.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {
 private Integer trainerId;
 private String firstName;
 private String lastName;
 private String expertise;
 private String email;
  
}
