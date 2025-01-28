package com.global.coursemanagementsystem.reppository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.coursemanagementsystem.entity.TrainingSession;
@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession,Long>{
    
}
