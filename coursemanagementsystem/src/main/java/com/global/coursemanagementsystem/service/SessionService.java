package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.TrainingSession;
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

    private TrainingSessionMapper trainingSessionMapper;

    public CourseSessionDTO getSessionByCourseId(int id) {
        List<Optional<TrainingSession>> sessions = trainingSessionRepository.findSessionsById(id);
        List<TrainingSessionDTO> sessionsDTO;
        if (!sessions.isEmpty()) {

            sessionsDTO = sessions.stream().map(session -> {
                TrainingSessionDTO sessionDTO = trainingSessionMapper.toDTO(session.get());
                return sessionDTO;
            }).collect(Collectors.toList());
        } else {
            throw new ResourceNotFoundException("there is no sessions for this course");
        }
        CourseSessionDTO courseSessionDTO = new CourseSessionDTO();
        courseSessionDTO.setSessions(sessionsDTO);
        courseSessionDTO.setCourseDTO(courseService.getCourseById(id));

        return courseSessionDTO ;
    }

    public TrainingSessionDTO addSession(AddTrainingSessionRequest sessionRequest) {
      Optional<TrainingSession> trainingSession =trainingSessionRepository.findById(sessionRequest.getSessionId());
       
      return null;
    }

    public TrainingSessionDTO updateSession(TrainingSessionDTO sessionDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSession'");
    }

    public List<TrainingSessionDTO> getAllSessions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllSessions'");
    }

}
