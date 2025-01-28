package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.TraineeMapper;
import com.global.coursemanagementsystem.reppository.TraineeRepository;
import com.global.coursemanagementsystem.request.AddingTraineeRequest;
import com.global.coursemanagementsystem.request.AddingTraineeRequest;

public class TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;
    @Autowired
    private TraineeMapper traineeMapper;

    public List<TraineeDTO> getAllTrainees() {
        return traineeRepository.findAll().stream().map(traineeMapper::toDTO).collect(Collectors.toList());

    }

    public TraineeDTO getTraineeById(Long id) {
        Optional<Trainee> trainee = traineeRepository.findById(id);
        TraineeDTO traineeDto;
        if (trainee.isPresent()) {
            traineeDto = traineeMapper.toDTO(trainee.get());
        } else {
            traineeDto = null;
        }
        return traineeDto;
    }

    public  TraineeDTO addTrainee(AddingTraineeRequest addingTraineeRequest) {
        Trainee currentTrainee = traineeMapper.toEntity(addingTraineeRequest);
        Trainee traineeHavingSameEmail =traineeRepository.findByEmail(currentTrainee.getEmail()).orElse(null);
        if(traineeHavingSameEmail!=null) {
            return null;
        }
        currentTrainee = traineeRepository.save(currentTrainee);
        return traineeMapper.toDTO(currentTrainee);
     

       
    }

    public TraineeDTO updateTrainee(TraineeDTO traineeDTO) {
        Trainee trainee = traineeMapper.toEntity(traineeDTO);
        TraineeDTO currentTrainee = getTraineeById(trainee.getTraineeId());
        currentTrainee.setLastName(trainee.getLastName());
        currentTrainee.setEmail(trainee.getEmail());
        currentTrainee.setTraineeId(trainee.getTraineeId());
        currentTrainee.setFirstName(trainee.getFirstName());
        trainee = traineeMapper.toEntity(traineeDTO);
        trainee = traineeRepository.save(trainee);
        return traineeMapper.toDTO(trainee);
    }

    public TraineeDTO deleteTrainee(Long id) {
        Trainee trainee = traineeRepository.findById(id).get();
        traineeRepository.delete(trainee);
        return traineeMapper.toDTO(trainee);
    }

}
