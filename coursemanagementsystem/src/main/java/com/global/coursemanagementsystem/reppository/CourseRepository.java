package com.global.coursemanagementsystem.reppository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.coursemanagementsystem.entity.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>{
    
}
