package com.global.coursemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.global.coursemanagementsystem.mapstruct.dto.CourseDTO;
import com.global.coursemanagementsystem.request.AddCourseRequest;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.CourseService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private CourseService courseService;

    @GetMapping("/get-all-courses")
    public  ResponseEntity<ApiResponse> getAllCourses() {
        List<CourseDTO>courses = courseService.getAllCourses();
        return ResponseEntity.ok(new ApiResponse("get all the courses",courses)); 
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse> getCourseById(@PathVariable long id) {
      CourseDTO courseDTO= courseService.getCourseById(id);
      if(courseDTO==null)
        return ResponseEntity.ok(new ApiResponse("the course is not found ",null));
        else
        return ResponseEntity.ok(new ApiResponse("the course is found ",courseDTO));
    }
    @PostMapping("/add-course")
    public ResponseEntity<ApiResponse> addCourse(@RequestBody AddCourseRequest CourseRequest) {
        CourseDTO courseDTO =courseService.addCourse(CourseRequest); 
        return ResponseEntity.ok(new ApiResponse("the course is added now",courseDTO));
    }

   
    
    

}
