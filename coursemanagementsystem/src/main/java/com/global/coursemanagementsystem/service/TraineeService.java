package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.entity.Trainer;
import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.TraineeMapper;
import com.global.coursemanagementsystem.reppository.TraineeRepository;
import com.global.coursemanagementsystem.request.AddTraineeRequest;

import lombok.RequiredArgsConstructor;

import com.global.coursemanagementsystem.request.AddTraineeRequest;

@Service
@RequiredArgsConstructor
public class TraineeService {


    private TraineeRepository traineeRepository;

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

    public TraineeDTO addTrainee(AddTraineeRequest TraineeRequest) {
        Trainee currentTrainee = traineeMapper.toEntity(TraineeRequest);
        Trainee traineeHavingSameId = traineeRepository.findById(currentTrainee.getTraineeId()).orElse(null);
        Trainee traineeHavingSameEmail = traineeRepository.findByEmail(currentTrainee.getEmail()).orElse(null);
        if (traineeHavingSameEmail != null||traineeHavingSameId != null) {
            throw new ResourceFoundException("Trainee with the same id or email already exists");
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

        Optional<Trainee> trainee = traineeRepository.findById(id);
        Trainee TraineeToBeDeleted;
        if (trainee.isPresent()) {
            TraineeToBeDeleted = trainee.get();
            traineeRepository.delete(TraineeToBeDeleted);
        } else {
            return null;
        }

        return traineeMapper.toDTO(TraineeToBeDeleted);
    }

}
