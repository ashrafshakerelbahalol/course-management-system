package com.global.coursemanagementsystem.reppository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.coursemanagementsystem.entity.Enrollement;
@Repository
public interface EnrollementRepository extends JpaRepository<Enrollement,Long>{
    
}
