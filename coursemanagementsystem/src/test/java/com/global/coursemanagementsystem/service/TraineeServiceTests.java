package com.global.coursemanagementsystem.service;


import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.TraineeMapper;
import com.global.coursemanagementsystem.reppository.TraineeRepository;


@ExtendWith(MockitoExtension.class)
public class TraineeServiceTests {
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private TraineeMapper traineeMapper;
    @InjectMocks
    private TraineeService traineeService;

    private Trainee trainee;
    private TraineeDTO traineeDTO;
    
    @BeforeEach
    public void init(){
        trainee=Trainee.builder()
        .email("ashrafshaker@gmail.com")
        .department("hello")
        .firstName("ashraf")
        .lastName("shaker")
        .build();

        traineeDTO= TraineeDTO.builder()  
        .email("ashrafshaker@gmail.com")
        .department("hello")
        .firstName("ashraf")
        .lastName("shaker")
        .build();

    }

    @Test
    public void TraineeService_GetALLTrainee_ReturnReview(){
      List<Trainee>trainees= Collections.singletonList(trainee);
        when(traineeMapper.toDTO(trainee)).thenReturn(traineeDTO);
        when(traineeRepository.findAll()).thenReturn(trainees);
        List<TraineeDTO> traineeDTOs = traineeService.getAllTrainees();
        Assertions.assertThat(traineeDTOs).isNotNull();
    }
    @Test
    public void TraineeService_GetTraineeByID_RetunTraineeDTO(){
        when(traineeMapper.toDTO(trainee)).thenReturn(traineeDTO);
        when(traineeRepository.findById(1L)).thenReturn(Optional.of(trainee));
        TraineeDTO traineeDTO = traineeService.getTraineeById(1L);
        Assertions.assertThat(traineeDTO).isNotNull();
    }



}
