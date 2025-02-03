package com.global.coursemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.global.coursemanagementsystem.entity.Trainer;
import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.TrainerDTO;
import com.global.coursemanagementsystem.request.AddTrainerRequest;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.TrainerService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private TrainerService trainerService;

    @GetMapping("/get-all-trainers")
    public ResponseEntity<ApiResponse> getAllTrainers() {
        List<TrainerDTO> trainers = trainerService.getAllTrainers();
        return ResponseEntity.ok(new ApiResponse("get all the trainers", trainers));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse> getTrainerById(@PathVariable Integer id) {
        try {
            TrainerDTO trainer = trainerService.getTrainerById(id);
            return ResponseEntity.ok(new ApiResponse("the trainer is found", trainer));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PostMapping("/add-trainer")
    public ResponseEntity<ApiResponse> addTrainer(@RequestBody AddTrainerRequest TrainerRequest) {
        try {
            TrainerDTO trainerDTO = trainerService.addTrainer(TrainerRequest);
            return ResponseEntity.ok(new ApiResponse("the trainer is created", trainerDTO));
        } catch (ResourceFoundException e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update-trainer")
    public ResponseEntity<ApiResponse> updateTrainer(@RequestBody TrainerDTO trainerDTO) {
        try {
            TrainerDTO trainer = trainerService.updateTrainer(trainerDTO);
            return ResponseEntity.ok(new ApiResponse("the trainer is updated", trainer));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete-trainer/{id}")
    public ResponseEntity<ApiResponse> deleteTrainer(@PathVariable Integer id) {
        try {
            TrainerDTO trainer = trainerService.deleteTrainer(id);
            return ResponseEntity.ok(new ApiResponse("the trainer is deleted", trainer));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
