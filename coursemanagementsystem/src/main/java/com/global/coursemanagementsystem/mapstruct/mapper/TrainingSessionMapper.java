package com.global.coursemanagementsystem.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.global.coursemanagementsystem.entity.TrainingSession;
import com.global.coursemanagementsystem.mapstruct.dto.TrainingSessionDTO;
import com.global.coursemanagementsystem.request.AddTrainingSessionRequest;

@Mapper(componentModel = "spring")
public interface TrainingSessionMapper {
    @Mapping(source = "course.courseName", target = "courseName")
    @Mapping(expression = "java(combineNames(trainingSession.getTrainer().getFirstName(), trainingSession.getTrainer().getLastName()))", 
    target = "trainerName")
    public TrainingSessionDTO toDTO(TrainingSession trainingSession);

    @Mapping(target = "course.courseName", source = "courseName")
    @Mapping(target = "trainer.firstName", expression="java(getFirstName(trainingSessionDTO.getTrainerName()))")
    @Mapping(target = "trainer.lastName", expression="java(getLastName(trainingSessionDTO.getTrainerName()))")
    public TrainingSession toEntity(TrainingSessionDTO trainingSessionDTO);

    // Helper Methods
    @Named("getFirstName")
     default String getFirstName(String trainerName){
        if(trainerName!= null && trainerName.contains(" "))
         return trainerName.split(" ")[0];
       else return trainerName;

     }
     @Named("getLastName")
     default String getLastName(String trainerName){
        if(trainerName!= null && trainerName.contains(" "))
         return trainerName.split(" ")[1];
       else return "";

     }
 default String combineNames(String firstName, String lastName) {
        if (firstName == null && lastName == null) {
            return null;
        }
        return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }

    public TrainingSession toEntity(AddTrainingSessionRequest trainingSessionRequest);

}
