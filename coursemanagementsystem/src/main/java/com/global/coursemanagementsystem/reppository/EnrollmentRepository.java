package com.global.coursemanagementsystem.reppository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.coursemanagementsystem.entity.Enrollment;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{
    @Query("SELECT e FROM Enrollment e WHERE e.trainingSession.sessionId = :sessionId AND e.trainee.traineeId = :traineeId")
    Optional<Enrollment> findBySessionIdAndTraineeId(Long sessionId, Long traineeId);
    
    List<Enrollment> findByTrainingSession_SessionId(Long id);

     
    
}
