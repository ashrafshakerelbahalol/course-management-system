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

import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.TrainingSessionDTO;
import com.global.coursemanagementsystem.request.AddTrainingSessionRequest;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.SessionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {
    private SessionService sessionService;
    @GetMapping("/get-session-by-course-id/{id}")
    public ResponseEntity<ApiResponse> getSessionByCourseId (@PathVariable Long id) {
try{
        List<TrainingSessionDTO> sessions = sessionService.getSessionByCourseId(id);
        return ResponseEntity.ok(new ApiResponse("get all the sessions with course", sessions));
   }catch(ResourceNotFoundException e){
     return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
   }
 }
    @PostMapping("/add-session")
    public ResponseEntity<ApiResponse> addSession (@RequestBody AddTrainingSessionRequest sessionRequest) {
                TrainingSessionDTO trainingSessionDTO = sessionService.addSession(sessionRequest);
        return  ResponseEntity.ok(new ApiResponse("get all the sessions with course", trainingSessionDTO));
    }
    
    
}
