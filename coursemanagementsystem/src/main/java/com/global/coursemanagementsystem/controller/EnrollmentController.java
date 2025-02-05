package com.global.coursemanagementsystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.EnrollmentDTO;
import com.global.coursemanagementsystem.mapstruct.dto.SessionEnrollmentDTO;
import com.global.coursemanagementsystem.request.AddEnrollmentRequest;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("enrollment")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping("/get-all-enrollments")
    public ResponseEntity<ApiResponse> getMethodName() {
        try {
            List<EnrollmentDTO> EnrollmentDTOs = enrollmentService.getAllEnrollments();
            return ResponseEntity.ok(new ApiResponse("get all the the enrollments", EnrollmentDTOs));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(204).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));

        }
    }

    @GetMapping("/get-enrollment-by-session-id/{id}")
    public ResponseEntity<ApiResponse> getEnrollmentBySessionId(@PathVariable Long id) {
        try {
            SessionEnrollmentDTO sessionEnrollmentDTO = enrollmentService.getEnrollmentBySessionId(id);
            return ResponseEntity.ok(new ApiResponse("get all the enrollments with session", sessionEnrollmentDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add-enrollment")
    public ResponseEntity<ApiResponse> addEnrollment(@RequestBody AddEnrollmentRequest enrollmentRequest) {
        try {
            EnrollmentDTO EnrollmentDTO = enrollmentService.addEnrollment(enrollmentRequest);
            return ResponseEntity.ok(new ApiResponse("add enrollment to a session", EnrollmentDTO));
        } catch (ResourceFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update-enrollment")
    public ResponseEntity<ApiResponse> updateEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        try {
            EnrollmentDTO EnrollmentDTO = enrollmentService.updateEnrollment(enrollmentDTO);
            return ResponseEntity.ok(new ApiResponse("update enrollment", EnrollmentDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
