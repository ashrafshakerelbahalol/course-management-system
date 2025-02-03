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
import com.global.coursemanagementsystem.mapstruct.dto.TraineeDTO;
import com.global.coursemanagementsystem.request.AddTraineeRequest;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.TraineeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainee")
public class TraineeController {
    private TraineeService traineeService;

    @GetMapping("/get-all-trainees")
    public ResponseEntity<ApiResponse> getAllTrainees() {
        List<TraineeDTO> trainees = traineeService.getAllTrainees();
        return ResponseEntity.ok(new ApiResponse("get all the trainees", trainees));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse> getTraineeById(@PathVariable Long id) {
        TraineeDTO trainee = traineeService.getTraineeById(id);
        if (trainee == null)
            return ResponseEntity.ok(new ApiResponse("the trainee is not found", trainee));
        else
            return ResponseEntity.ok(new ApiResponse("the trainee is found", trainee));
    }

    @PostMapping("/add-trainee")
    public ResponseEntity<ApiResponse> addTrainee(@RequestBody AddTraineeRequest TraineeRequest) {
        try {
            TraineeDTO traineeDTO = traineeService.addTrainee(TraineeRequest);
            return ResponseEntity.ok(new ApiResponse("the trainee is created", traineeDTO));
        } catch (ResourceFoundException e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update-trainee")
    public ResponseEntity<ApiResponse> updateTrainee(@RequestBody TraineeDTO traineeDTO) {
        TraineeDTO trainee = traineeService.updateTrainee(traineeDTO);
        return ResponseEntity.ok(new ApiResponse("the trainee is updated", trainee));

    }

    @DeleteMapping("/delete-trainee/{id}")
    public ResponseEntity<ApiResponse> deleteTrainee(@PathVariable Long id) {
        TraineeDTO trainee = traineeService.deleteTrainee(id);

        if (trainee == null)
            return ResponseEntity.ok(new ApiResponse("the trainee is not found", trainee));
        else
            return ResponseEntity.ok(new ApiResponse("the trainee is deleted", trainee));

    }
}
