package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.Trainer;
import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.TrainerDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.TrainerMapper;
import com.global.coursemanagementsystem.reppository.TrainerRepository;
import com.global.coursemanagementsystem.request.AddTrainerRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;

    private final TrainerMapper trainerMapper;

    public List<TrainerDTO> getAllTrainers(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Trainer> trainers =  trainerRepository.findAll(pageable);
        List <TrainerDTO> trainerDTO= trainers.stream().map(trainerMapper::toDTO).toList();
        if(trainerDTO.isEmpty()) {
            throw new ResourceNotFoundException("No trainers is found");
        }
        
        return trainerDTO;

    }

    public TrainerDTO getTrainerById(Integer id) {
        Optional<Trainer> trainer = trainerRepository.findById(id);
        TrainerDTO trainerDto;
        if (trainer.isPresent()) {
            trainerDto = trainerMapper.toDTO(trainer.get());
        } else {
            throw new ResourceNotFoundException("Trainer with the id: "+id+" is not found");
        }
        return trainerDto;
    }

    public  TrainerDTO addTrainer(AddTrainerRequest TrainerRequest) {
        Trainer currentTrainer = trainerMapper.toEntity(TrainerRequest);
        Trainer trainerHavingSameId =trainerRepository.findById(currentTrainer.getTrainerId()).orElse(null);
        Trainer trainerHavingSameEmail =trainerRepository.findByEmail(currentTrainer.getEmail()).orElse(null);
        if(trainerHavingSameEmail!=null||trainerHavingSameId!=null) {
            throw new ResourceFoundException("Trainer with the same id or email already exists");
        }
        currentTrainer = trainerRepository.save(currentTrainer);
        return trainerMapper.toDTO(currentTrainer);
     

       
    }

    public TrainerDTO updateTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = trainerMapper.toEntity(trainerDTO);
        Trainer currentTrainer = trainerRepository.findById(trainer.getTrainerId()).orElseThrow
                (()-> new ResourceNotFoundException("Trainer with the id: "+trainerDTO.getTrainerId()+" is not found"));
        BeanUtils.copyProperties(trainer, currentTrainer, "trainerId","createdAt", "createdBy", "updatedAt", "updatedBy");
        currentTrainer = trainerRepository.save(currentTrainer);
        return trainerMapper.toDTO(currentTrainer);
    }

    public TrainerDTO deleteTrainer(Integer id) {
         Optional<Trainer> trainer = trainerRepository.findById(id);
        Trainer TrainerToBeDeleted;
        if (trainer.isPresent()) {
            TrainerToBeDeleted=trainer.get();
            trainerRepository.delete(TrainerToBeDeleted);
        } else {
         throw new ResourceNotFoundException("Trainer with the id not found");
        }
        
        return trainerMapper.toDTO(TrainerToBeDeleted);
    }

}
