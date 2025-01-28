package com.global.coursemanagementsystem.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.request.AddingTraineeRequest;

@Mapper(componentModel = "spring")
public interface TraineeMapper  {
     TraineeMapper INSTANCE = Mappers.getMapper(TraineeMapper.class);
    TraineeDTO toDTO(Trainee trainee);
    Trainee toEntity(TraineeDTO traineeDTO);
    Trainee toEntity(AddingTraineeRequest addingTraineeRequest);
}
