package com.global.coursemanagementsystem.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.global.coursemanagementsystem.entity.Course;

import com.global.coursemanagementsystem.mapstruct.dto.CourseDTO;
import com.global.coursemanagementsystem.mapstruct.dto.CourseSessionDTO;
import com.global.coursemanagementsystem.request.AddCourseRequest;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO toDTO(Course course);

    Course toEntity(CourseDTO courseDTO);

    Course toEntity(AddCourseRequest CourseRequest);
   
}
