package com.global.coursemanagementsystem.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.global.coursemanagementsystem.entity.Enrollment;
import com.global.coursemanagementsystem.entity.TrainingSession;
import com.global.coursemanagementsystem.mapstruct.dto.EnrollmentDTO;
import com.global.coursemanagementsystem.mapstruct.dto.TrainingSessionDTO;
import com.global.coursemanagementsystem.request.AddEnrollmentRequest;
import com.global.coursemanagementsystem.request.AddTrainingSessionRequest;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
  EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

  @Mapping(target =   "trainingSessionId", source  = "enrollment.trainingSession.sessionId")
  @Mapping(target = "traineeName", expression = "java(combineNames(enrollment.getTrainee().getFirstName(),enrollment.getTrainee().getLastName()))")
  EnrollmentDTO toDTO(Enrollment enrollment);
  
  @Mapping(target = "trainee.firstName", expression = "java(getFirstName(enrollmentDTO.getTraineeName()))")
  @Mapping(target = "trainee.lastName", expression = "java(getLastName(enrollmentDTO.getTraineeName()))")
  @Mapping(source = "enrollmentDTO.trainingSessionId",target = "trainingSession.sessionId")
  Enrollment toEntity(EnrollmentDTO enrollmentDTO);

  Enrollment toEntity(AddEnrollmentRequest enrollmentRequest);

  @Named("getFirstName")
  default String getFirstName(String traineeName) {
    if (traineeName != null && traineeName.contains(" "))
      return traineeName.split(" ")[0];
    else
      return traineeName;

  }

  @Named("getLastName")
  default String getLastName(String traineeName) {
    if (traineeName != null && traineeName.contains(" "))
      return traineeName.split(" ")[1];
    else
      return "";

  }

  @Named("combineNames")
  default String combineNames(String firstName, String lastName) {
    if (firstName == null && lastName == null) {
      return null;
    }
    return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");

  }


}
