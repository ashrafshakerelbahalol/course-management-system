package com.global.coursemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.request.AddTraineeRequest;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.TraineeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainee")
public class TraineeController {
    private final TraineeService traineeService;

    @GetMapping("/get-all-trainees")
    public ResponseEntity<ApiResponse> getAllTrainees() {
        try {
            List<TraineeDTO> trainees = traineeService.getAllTrainees();
            return ResponseEntity.ok(new ApiResponse("get all the trainees", trainees));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse> getTraineeById(@PathVariable Long id) {
        try {
            TraineeDTO trainee = traineeService.getTraineeById(id);
            return ResponseEntity.ok(new ApiResponse("the trainee is found", trainee));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PostMapping("/add-trainee")
    public ResponseEntity<ApiResponse> addTrainee(@RequestBody @Valid AddTraineeRequest TraineeRequest) {
        try {
            TraineeDTO traineeDTO = traineeService.addTrainee(TraineeRequest);
            return ResponseEntity.ok(new ApiResponse("the trainee is added now", traineeDTO));
        } catch (ResourceFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update-trainee")
    public ResponseEntity<ApiResponse> updateTrainee(@RequestBody TraineeDTO traineeDTO) {
        try {
            TraineeDTO trainee = traineeService.updateTrainee(traineeDTO);
            return ResponseEntity.ok(new ApiResponse("the trainee is updated", trainee));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete-trainee/{id}")
    public ResponseEntity<ApiResponse> deleteTrainee(@PathVariable Long id) {
        try {
            TraineeDTO trainee = traineeService.deleteTrainee(id);
            return ResponseEntity.ok(new ApiResponse("the trainee is deleted", trainee));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
