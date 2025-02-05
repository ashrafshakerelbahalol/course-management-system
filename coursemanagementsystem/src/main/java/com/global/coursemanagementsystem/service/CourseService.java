package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.coursemanagementsystem.entity.Course;
import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.CourseDTO;
import com.global.coursemanagementsystem.mapstruct.mapper.CourseMapper;
import com.global.coursemanagementsystem.reppository.CourseRepository;
import com.global.coursemanagementsystem.request.AddCourseRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOs = courses.stream().map(courseMapper::toDTO).toList();
        if (courseDTOs.isEmpty())
            throw new ResourceNotFoundException("there is no courses added");
        return courseDTOs;

    }

    public CourseDTO getCourseById(int id) {
        Optional<Course> courseTobeFound = courseRepository.findById(id);
        CourseDTO courseDTO;
        if (courseTobeFound.isPresent())
            courseDTO = courseMapper.toDTO(courseTobeFound.get());
        else
            throw new ResourceNotFoundException("there is no course with id " + id);
        return courseDTO;

    }

    public CourseDTO addCourse(AddCourseRequest CourseRequest) {
        Course courseToSaved = courseMapper.toEntity(CourseRequest);
        Optional<Course> courseWithSameId = courseRepository.findById(courseToSaved.getCourseId());
        if (courseWithSameId.isPresent())
            throw new ResourceFoundException("there is a course with the same id");
        courseToSaved = courseRepository.save(courseToSaved);
        CourseDTO courseDTO = courseMapper.toDTO(courseToSaved);
        return courseDTO;

    }

    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Course courseToUpdated = courseMapper.toEntity(courseDTO);
        Optional<Course> courseWithSameId = courseRepository.findById(courseToUpdated.getCourseId());
        if (courseWithSameId.isEmpty())
            throw new ResourceNotFoundException("there is no course with the same id");
        courseToUpdated = courseRepository.save(courseToUpdated);
        courseDTO = courseMapper.toDTO(courseToUpdated);
        return courseDTO;
    }

}
