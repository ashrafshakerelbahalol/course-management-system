package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.Trainer;
import com.global.coursemanagementsystem.entity.TrainingSession;
import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.CourseSessionDTO;
import com.global.coursemanagementsystem.mapstruct.dto.TrainingSessionDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.TrainingSessionMapper;
import com.global.coursemanagementsystem.reppository.TrainingSessionRepository;
import com.global.coursemanagementsystem.request.AddTrainingSessionRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final TrainingSessionRepository trainingSessionRepository;
    private final CourseService courseService;
    private final TrainingSessionMapper trainingSessionMapper;

    public CourseSessionDTO getSessionByCourseId(int id) {
        List<Optional<TrainingSession>> sessions = trainingSessionRepository.findSessionsById(id);
        List<TrainingSessionDTO> sessionsDTO;
        if (!sessions.isEmpty()) {
            sessionsDTO = sessions.stream().map(session -> {
                TrainingSessionDTO sessionDTO = trainingSessionMapper.toDTO(session.get());
                return sessionDTO;
            }).toList();
        } else {
            throw new ResourceNotFoundException("there is no sessions for this course");
        }
        CourseSessionDTO courseSessionDTO = new CourseSessionDTO();
        courseSessionDTO.setSessions(sessionsDTO);
        courseSessionDTO.setCourseDTO(courseService.getCourseById(id));

        return courseSessionDTO ;
    }

    public TrainingSessionDTO addSession(AddTrainingSessionRequest sessionRequest) {
         TrainingSession currentTrainingSession = trainingSessionMapper.toEntity(sessionRequest);
         currentTrainingSession = trainingSessionRepository.save(currentTrainingSession);
        return trainingSessionMapper.toDTO(currentTrainingSession);

    }

    public TrainingSessionDTO updateSession(TrainingSessionDTO sessionDTO) {
        TrainingSession currentTrainingSession = trainingSessionMapper.toEntity(sessionDTO);
        TrainingSession sessionHavingSameId =trainingSessionRepository.findById(currentTrainingSession.getSessionId()).orElse(null);
    
        if(sessionHavingSameId!=null) {
            throw new ResourceNotFoundException("There is no session with the same id");
        }
        currentTrainingSession = trainingSessionRepository.save(currentTrainingSession);
        return trainingSessionMapper.toDTO(currentTrainingSession);
    }

    public List<TrainingSessionDTO> getAllSessions() {
            return trainingSessionRepository.findAll().stream().map(trainingSessionMapper::toDTO).toList();
       
    }

    public TrainingSessionDTO findById(Long id) {
        TrainingSession trainingSession = trainingSessionRepository.findById(id)
           .orElseThrow(()-> new ResourceNotFoundException("there is no session with the id "+id));
        return trainingSessionMapper.toDTO(trainingSession);
 
        }


}
