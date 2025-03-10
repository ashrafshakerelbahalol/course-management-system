package com.global.coursemanagementsystem.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.global.coursemanagementsystem.entity.Trainee;
import com.global.coursemanagementsystem.entity.Trainer;
import com.global.coursemanagementsystem.reppository.TraineeRepository;
import com.global.coursemanagementsystem.reppository.TrainerRepository;

@DataJpaTest
@ActiveProfiles("test")
public class TrainerRepositoryTest {
    @Autowired
    private TrainerRepository trainerRepository;
    @AfterEach
    void tearDown() {
        trainerRepository.deleteAll();
    }
    

    @Test
    void ItShouldCheckEmailExists() {
        // Given
        String email = "ashrafshaker@gmail.com";
        Trainer trainer = Trainer.builder().firstName("ashraf").email(email).trainerId(123).
        lastName("shaker").build();
        trainerRepository.save(trainer);
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
