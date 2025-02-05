package com.global.coursemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.coursemanagementsystem.error.ResourceFoundException;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.CourseSessionDTO;
import com.global.coursemanagementsystem.mapstruct.dto.TrainingSessionDTO;
import com.global.coursemanagementsystem.request.AddTrainingSessionRequest;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.SessionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {
  private final SessionService sessionService;

  @GetMapping("/get-all-sessions")
  public ResponseEntity<ApiResponse> getMethodName() {
    try {
      List<TrainingSessionDTO> trainingSessionDTOs = sessionService.getAllSessions();
      return ResponseEntity.ok(new ApiResponse("get all the the sessions", trainingSessionDTOs));
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(204).body(new ApiResponse(e.getMessage(), null));
    } catch (RuntimeException e) {
      return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));

    }
  }

  @GetMapping("/get-session-by-course-id/{id}")
  public ResponseEntity<ApiResponse> getSessionByCourseId(@PathVariable int id) {
    try {
      CourseSessionDTO courseSessionDTO = sessionService.getSessionByCourseId(id);
      return ResponseEntity.ok(new ApiResponse("get all the sessions with course", courseSessionDTO));
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
    }
  }

  @PostMapping("/add-session")
  public ResponseEntity<ApiResponse> addSession(@RequestBody AddTrainingSessionRequest sessionRequest) {
    try {
      TrainingSessionDTO trainingSessionDTO = sessionService.addSession(sessionRequest);
      return ResponseEntity.ok(new ApiResponse("add session to a course", trainingSessionDTO));
    } catch (ResourceFoundException e) {
      return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
    } catch (RuntimeException e) {
      return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
    }
  }

  @PutMapping("/update-session")
  public ResponseEntity<ApiResponse> updateSession(@RequestBody TrainingSessionDTO sessionDTO) {
    try {
      TrainingSessionDTO trainingSessionDTO = sessionService.updateSession(sessionDTO);
      return ResponseEntity.ok(new ApiResponse("update session", trainingSessionDTO));
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), null));
    } catch (RuntimeException e) {
      return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), null));
    }
  }

}
