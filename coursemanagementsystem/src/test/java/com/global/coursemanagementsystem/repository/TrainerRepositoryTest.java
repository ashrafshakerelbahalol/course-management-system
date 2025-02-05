package com.global.coursemanagementsystem.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.reppository.TraineeRepository;

@DataJpaTest
public class TrainerRepositoryTest {
    @Autowired
    private TraineeRepository trainerRepository;
    @AfterEach
    void tearDown() {
        trainerRepository.deleteAll();
    }
    

    @Test
    void ItShouldCheckEmailExists() {
        // Given
        String email = "ashrafshaker@gmail.com";
        Trainee trainee = Trainee.builder().firstName("ashraf").email(email).traineeId(123L).
        lastName("shaker").build();
        trainerRepository.save(trainee);
        // When
        boolean expected = trainerRepository.findByEmail(email).isPresent();
        // Then
        assertTrue(expected);
    }
    @Test
    void ItShouldCheckEmailDoesnotExists() {
        // Given
        String email = "ashrafshaker@gmail.com";
       
        // When
        boolean expected = trainerRepository.findByEmail(email).isPresent();
        // Then
        assertFalse(expected);
    }
}
