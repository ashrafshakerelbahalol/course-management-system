package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.Enrollment;
import com.global.coursemanagementsystem.entity.TrainingSession;
import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.EnrollmentDTO;
import com.global.coursemanagementsystem.mapstruct.dto.SessionEnrollmentDTO;
import com.global.coursemanagementsystem.mapstruct.dto.TrainingSessionDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.EnrollmentMapper;
import com.global.coursemanagementsystem.reppository.EnrollmentRepository;
import com.global.coursemanagementsystem.reppository.TraineeRepository;
import com.global.coursemanagementsystem.request.AddEnrollmentRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final SessionService sessionService;
    private final TraineeRepository traineeRepository;

    public EnrollmentDTO addEnrollment(AddEnrollmentRequest enrollmentRequest) {
        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentRequest);
      /*   Optional<Enrollment> enrollmentToFind = enrollmentRepository.findBySessionIdAndTraineeId(
                enrollment.getTrainingSession().getSessionId(), enrollment.getTrainee().getTraineeId());
        if (enrollmentToFind.isPresent()) {
            throw new ResourceFoundException("This trainee is already enrolled in this session");
        }*/
        enrollment = enrollmentRepository.save(enrollment);
        enrollment.setTrainee(traineeRepository.findById(enrollment.getTrainee().getTraineeId()).orElseThrow(()->new ResourceNotFoundException("the trainee is not found")));
        return enrollmentMapper.toDTO(enrollment);
    }

    public EnrollmentDTO updateEnrollment(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentDTO);
        Optional<Enrollment> enrollmentHavingSameId = enrollmentRepository.findById(enrollment.getEnrollmentId());
        if (enrollmentHavingSameId.isPresent())
            throw new ResourceNotFoundException("This enrollment does not exist");
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDTO(enrollment);
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        if (enrollments.isEmpty())
            throw new ResourceNotFoundException("No Enrollments found");
        List<EnrollmentDTO> enrollmentDTOs = enrollments.stream().map(enrollmentMapper::toDTO).toList();
        return enrollmentDTOs;
    }

    public EnrollmentDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Enrollment found with id"));
        return enrollmentMapper.toDTO(enrollment);
    }

    public SessionEnrollmentDTO getEnrollmentBySessionId(Long id) {
        List<Enrollment> enrollments = enrollmentRepository.findByTrainingSession_SessionId(id);
        if (enrollments.isEmpty())
            throw new ResourceNotFoundException("NoEnrollments found for this session");
        List<EnrollmentDTO> enrollmentDTOs = enrollments.stream().map(enrollmentMapper::toDTO).toList();
        TrainingSessionDTO sessionDTO = sessionService.findById(id);
        SessionEnrollmentDTO sessionEnrollmentDTO = new SessionEnrollmentDTO();
        sessionEnrollmentDTO.setEnrollmentDtos(enrollmentDTOs);
        sessionEnrollmentDTO.setTrainingSessionDTO(sessionDTO);
        return sessionEnrollmentDTO;
    }

    public void deleteEnrollment(Long id) {
       enrollmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("the resourse is not found to be deleted")); 
       enrollmentRepository.deleteById(id);

    }

}
