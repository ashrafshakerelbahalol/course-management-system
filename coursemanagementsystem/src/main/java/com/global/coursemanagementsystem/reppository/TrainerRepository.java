package com.global.coursemanagementsystem.reppository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.coursemanagementsystem.entity.Trainer;
@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Integer>{
   
   Optional <Trainer> findByEmail(String email);
    
}
