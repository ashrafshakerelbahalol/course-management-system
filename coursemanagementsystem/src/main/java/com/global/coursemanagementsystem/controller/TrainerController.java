package com.global.coursemanagementsystem.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.TrainerDTO;
import com.global.coursemanagementsystem.request.AddTrainerRequest;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.TrainerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllTrainers(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        try {
            List<TrainerDTO> trainers = trainerService.getAllTrainers(page,size);
            return ResponseEntity.ok(new ApiResponse("Getting all the trainers with the page number " + page + "and size "+ size, trainers));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTrainerById(@PathVariable Integer id) {
        try {
            TrainerDTO trainer = trainerService.getTrainerById(id);
            return ResponseEntity.ok(new ApiResponse("The trainer is found", trainer));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PostMapping()
    public ResponseEntity<ApiResponse> addTrainer(@RequestBody @Valid AddTrainerRequest TrainerRequest) {
        try {
            TrainerDTO trainerDTO = trainerService.addTrainer(TrainerRequest);
            return ResponseEntity.status(201).body(new ApiResponse("The trainer is added now", trainerDTO));
        } catch (ResourceFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> updateTrainer(@RequestBody TrainerDTO trainerDTO) {
        try {
            TrainerDTO trainer = trainerService.updateTrainer(trainerDTO);
            return ResponseEntity.ok(new ApiResponse("The trainer is updated", trainer));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTrainer(@PathVariable Integer id) {
        try {
            TrainerDTO trainer = trainerService.deleteTrainer(id);
            return ResponseEntity.ok(new ApiResponse("The trainer is deleted", trainer));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
