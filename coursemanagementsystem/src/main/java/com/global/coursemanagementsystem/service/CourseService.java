package com.global.coursemanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<CourseDTO> getAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses =  courseRepository.findAll(pageable);
        List<CourseDTO> courseDTOs = courses.stream().map(courseMapper::toDTO).toList();
        if (courseDTOs.isEmpty())
            throw new ResourceNotFoundException("There is no courses added");
        return courseDTOs;

    }

    public CourseDTO getCourseById(int id) {
        Optional<Course> courseTobeFound = courseRepository.findById(id);
        CourseDTO courseDTO;
        if (courseTobeFound.isPresent())
            courseDTO = courseMapper.toDTO(courseTobeFound.get());
        else
            throw new ResourceNotFoundException("There is no course with id " + id);
        return courseDTO;

    }

    public CourseDTO addCourse(AddCourseRequest CourseRequest) {
        Course courseToSaved = courseMapper.toEntity(CourseRequest);
        Optional<Course> courseWithSameId = courseRepository.findById(courseToSaved.getCourseId());
        if (courseWithSameId.isPresent())
            throw new ResourceFoundException("There is a course with the same id");
        courseToSaved = courseRepository.save(courseToSaved);
        CourseDTO courseDTO = courseMapper.toDTO(courseToSaved);
        return courseDTO;

    }

    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Course courseWithUpdatedData = courseMapper.toEntity(courseDTO);
        Course courseWithSameId = courseRepository.findById(courseWithUpdatedData.getCourseId())
                .orElseThrow(()->new ResourceNotFoundException("There is no such course with the that id"));
        BeanUtils.copyProperties(courseWithUpdatedData, courseWithSameId, "courseId", "createdAt", "createdBy", "updatedAt", "updatedBy");
        courseWithUpdatedData = courseRepository.save(courseWithUpdatedData);
        courseDTO = courseMapper.toDTO(courseWithUpdatedData);
        return courseDTO;
    }

    public void deleteCourse(int courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(()->new ResourceNotFoundException("There is no such course with the that id"));
        courseRepository.deleteById(courseId);
    }
}
