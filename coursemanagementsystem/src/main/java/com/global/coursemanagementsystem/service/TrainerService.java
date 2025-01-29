package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.entity.Trainer;
import com.global.coursemanagementsystem.mapstruct.dto.TrainerDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.TrainerMapper;
import com.global.coursemanagementsystem.reppository.TrainerRepository;
import com.global.coursemanagementsystem.request.AddTrainerRequest;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private TrainerMapper trainerMapper;

    public List<TrainerDTO> getAllTrainers() {
        return trainerRepository.findAll().stream().map(trainerMapper::toDTO).collect(Collectors.toList());

    }

    public TrainerDTO getTrainerById(Integer id) {
        Optional<Trainer> trainer = trainerRepository.findById(id);
        TrainerDTO trainerDto;
        if (trainer.isPresent()) {
            trainerDto = trainerMapper.toDTO(trainer.get());
        } else {
            trainerDto = null;
        }
        return trainerDto;
    }

    public  TrainerDTO addTrainer(AddTrainerRequest TrainerRequest) {
        Trainer currentTrainer = trainerMapper.toEntity(TrainerRequest);
        Trainer trainerHavingSameId =trainerRepository.findById(currentTrainer.getTrainerId()).orElse(null);
        Trainer trainerHavingSameEmail =trainerRepository.findByEmail(currentTrainer.getEmail()).orElse(null);
        if(trainerHavingSameEmail!=null||trainerHavingSameId!=null) {
            return null;
        }
        currentTrainer = trainerRepository.save(currentTrainer);
        return trainerMapper.toDTO(currentTrainer);
     

       
    }

    public TrainerDTO updateTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = trainerMapper.toEntity(trainerDTO);
        TrainerDTO currentTrainer = getTrainerById(trainer.getTrainerId());
        currentTrainer.setLastName(trainer.getLastName());
        currentTrainer.setEmail(trainer.getEmail());
        currentTrainer.setTrainerId(trainer.getTrainerId());
        currentTrainer.setFirstName(trainer.getFirstName());
        trainer = trainerMapper.toEntity(trainerDTO);
        trainer = trainerRepository.save(trainer);
        return trainerMapper.toDTO(trainer);
    }

    public TrainerDTO deleteTrainer(Integer id) {
         Optional<Trainer> trainer = trainerRepository.findById(id);
        Trainer TrainerToBeDeleted;
        if (trainer.isPresent()) {
            TrainerToBeDeleted=trainer.get();
            trainerRepository.delete(TrainerToBeDeleted);
        } else {
            return null ;
        }
        
        return trainerMapper.toDTO(TrainerToBeDeleted);
    }

}
