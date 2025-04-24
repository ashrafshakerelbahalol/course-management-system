package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.entity.Trainer;
import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.mapstruct.dto.TrainerDTO;
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.TraineeMapper;
import com.global.coursemanagementsystem.reppository.TraineeRepository;
import com.global.coursemanagementsystem.request.AddTraineeRequest;

import lombok.RequiredArgsConstructor;

import com.global.coursemanagementsystem.request.AddTraineeRequest;

@Service
@RequiredArgsConstructor
public class TraineeService {


    private final TraineeRepository traineeRepository;

    private final TraineeMapper traineeMapper;

    public List<TraineeDTO> getAllTrainees() {
        List <Trainee> trainers = traineeRepository.findAll();
        List <TraineeDTO> traineeDTO= trainers.stream().map(traineeMapper::toDTO).toList();
        if(traineeDTO.isEmpty()) {
            throw new ResourceNotFoundException("No trainees found");
        }
        
        return traineeDTO;

    }

    public TraineeDTO getTraineeById(Long id) {
        Optional<Trainee> trainee = traineeRepository.findById(id);
        TraineeDTO traineeDTO;
        if (trainee.isPresent()) {
            traineeDTO = traineeMapper.toDTO(trainee.get());
        } else {
          throw new ResourceNotFoundException("there is no trainee with id "+ id);
        }
        return traineeDTO;
    }

    public TraineeDTO addTrainee(AddTraineeRequest TraineeRequest) {
        Trainee currentTrainee = traineeMapper.toEntity(TraineeRequest);
        Trainee traineeHavingSameEmail = traineeRepository.findByEmail(currentTrainee.getEmail()).orElse(null);
        if (traineeHavingSameEmail != null) {
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
            throw new ResourceNotFoundException("Trainee with the id not found");
        }

        return traineeMapper.toDTO(TraineeToBeDeleted);
    }

}
