package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.Course;
import com.global.coursemanagementsystem.mapstruct.dto.CourseDTO;
import com.global.coursemanagementsystem.mapstruct.dto.TrainerDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.CourseMapper;
import com.global.coursemanagementsystem.reppository.CourseRepository;
import com.global.coursemanagementsystem.request.AddCourseRequest;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(courseMapper::toDTO).collect(Collectors.toList());

    }

    public CourseDTO getCourseById(Long id) {
        Optional<Course> courseTobeFound = courseRepository.findById(id);
        CourseDTO courseDTO;
        if (courseTobeFound.isPresent())
            courseDTO = courseMapper.toDTO(courseTobeFound.get());
        else
            courseDTO = null;
        return courseDTO;

    }

    public CourseDTO addCourse(AddCourseRequest CourseRequest) {
        Course courseToSaved = courseMapper.toEntity(CourseRequest);
        courseToSaved = courseRepository.save(courseToSaved);
        CourseDTO courseDTO = courseMapper.toDTO(courseToSaved);
        return courseDTO;

    }

}
