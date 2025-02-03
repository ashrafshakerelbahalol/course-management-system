package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.TrainingSession;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.TrainingSessionDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.TrainingSessionMapper;
import com.global.coursemanagementsystem.reppository.TrainingSessionRepository;
import com.global.coursemanagementsystem.request.AddTrainingSessionRequest;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SessionService {

    private TrainingSessionRepository trainingSessionRepository;
 
   private TrainingSessionMapper trainingSessionMapper;
    public List<TrainingSessionDTO> getSessionByCourseId(Long id) {
        List<Optional<TrainingSession>> sessions = trainingSessionRepository.findSessionsById(id);
        List<TrainingSessionDTO> sessionsDTO ;
        if (sessions.get(0).isPresent()) {
 
         sessionsDTO = sessions.stream().map(session -> {
            TrainingSessionDTO sessionDTO = trainingSessionMapper.toDTO(session.get());
            return sessionDTO;
        }).collect(Collectors.toList());}
        else 
         throw new ResourceNotFoundException("there is no sessions for this course");
        return sessionsDTO;
    }
    public TrainingSessionDTO addSession(AddTrainingSessionRequest sessionRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSession'");
    }

}
