package com.global.coursemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.coursemanagementsystem.entity.Assessment;
import com.global.coursemanagementsystem.error.ResourceNotFoundException;
import com.global.coursemanagementsystem.mapstruct.dto.AssessmentDTO;
import com.global.coursemanagementsystem.response.ApiResponse;
import com.global.coursemanagementsystem.service.AssessmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Assessment")
public class AssessmentController {
@Autowired
private AssessmentService assessmentService;
 /*@GetMapping("/get-all-assessment")
public ResponseEntity<ApiResponse> getAllAssessment() {
  try {
    List<AssessmentDTO> assessments=assessmentService.getAllAssessment();
    return ResponseEntity.ok(new ApiResponse("getting all the Assessment",assessments));
   } catch (ResourceNotFoundException e) {
    return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(),null));
   } catch(RuntimeException e){
    return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(),null));

   }
}
*/

}
