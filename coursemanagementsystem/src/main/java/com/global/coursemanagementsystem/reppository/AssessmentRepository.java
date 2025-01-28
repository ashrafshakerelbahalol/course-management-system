package com.global.coursemanagementsystem.reppository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.coursemanagementsystem.entity.Assessment;
@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Long>{
    
}
