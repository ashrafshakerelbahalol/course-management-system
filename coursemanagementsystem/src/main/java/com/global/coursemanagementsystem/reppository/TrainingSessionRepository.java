package com.global.coursemanagementsystem.reppository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.coursemanagementsystem.entity.TrainingSession;
@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession,Long>{
     @Query("Select e from TrainingSession e where e.course.courseId = :id")
     List<Optional<TrainingSession>>  findSessionsById(Long id);
    
}
