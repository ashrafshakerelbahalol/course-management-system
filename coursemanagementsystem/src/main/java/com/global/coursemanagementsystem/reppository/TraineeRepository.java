package com.global.coursemanagementsystem.reppository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.global.coursemanagementsystem.entity.Trainee;
@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Long>{

    Optional<Trainee> findByEmail(String email);
    
}
