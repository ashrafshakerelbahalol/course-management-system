package com.global.coursemanagementsystem.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.global.coursemanagementsystem.entity.Trainer;
import com.global.coursemanagementsystem.mapstruct.dto.TrainerDTO;
import com.global.coursemanagementsystem.request.AddingTrainerRequest;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
   
   
    TrainerMapper INSTANCE = Mappers.getMapper(TrainerMapper.class);
    TrainerDTO toDTO(Trainer trainer);
    Trainer toEntity(TrainerDTO trainerDTO);
    Trainer toEntity(AddingTrainerRequest addingTrainerRequest);
}
