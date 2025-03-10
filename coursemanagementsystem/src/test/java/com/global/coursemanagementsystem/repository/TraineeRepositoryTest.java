package com.global.coursemanagementsystem.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.reppository.TraineeRepository;

@DataJpaTest
@ActiveProfiles("test")
public class TraineeRepositoryTest {
    @Autowired
    private TraineeRepository traineeRepository;
    @AfterEach
    void tearDown() {
        traineeRepository.deleteAll();
    }
    

    @Test
    void ItShouldCheckEmailExists() {
        // Given
        String email = "ashrafshaker@gmail.com";
        Trainee trainee = Trainee.builder().firstName("ashraf").lastName("ashraf").email(email).
        lastName("shaker").build();
        traineeRepository.save(trainee);
        // When
        boolean expected = traineeRepository.findByEmail(email).isPresent();
        // Then
        assertTrue(expected);
    }
    @Test
    void ItShouldCheckEmailDoesnotExists() {
        // Given
        String email = "ashrafshaker@gmail.com";
       
        // When
        boolean expected = traineeRepository.findByEmail(email).isPresent();
        // Then
        assertFalse(expected);
    }
}
