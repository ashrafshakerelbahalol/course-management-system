package com.global.coursemanagementsystem.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.CourseDTO;
import com.global.coursemanagementsystem.request.AddCourseRequest;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllCourses(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            List<CourseDTO> courses = courseService.getAllCourses(page, size);
            return ResponseEntity.ok(new ApiResponse("get all the courses with the page number " + page + "and size " + size, courses));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PostMapping("")
    public ResponseEntity<ApiResponse> addCourse(@RequestBody AddCourseRequest CourseRequest) {
        try {
            CourseDTO courseDTO = courseService.addCourse(CourseRequest);
            return ResponseEntity.ok(new ApiResponse("The course is added right now", courseDTO));

        } catch (ResourceFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> updateCourse(@RequestBody CourseDTO courseDTO) {
        try {
            courseDTO = courseService.updateCourse(courseDTO);
            return ResponseEntity.ok(new ApiResponse("The course is updated now", courseDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> deleteCourse(@RequestParam int courseId) {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok(new ApiResponse("The course is deleted right now ", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }

    }


}
