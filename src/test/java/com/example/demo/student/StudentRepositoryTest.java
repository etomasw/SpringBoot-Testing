package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    /* Aftear each test we delete all the information */
    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void checkIfStudentEmailExists() {
        // Given
        String email = "jamila@gmail.com";
        Student student = new Student("Jamila",email,Gender.FEMALE);
        repository.save(student);

        boolean expected = repository.selectExistsEmail(email);
        assertThat(expected).isTrue();
    }
    @Test
    void checkIfStudentEmailNotExists() {
        // Given
        String email = "jamila@gmail.com";
        boolean result = repository.selectExistsEmail(email);
        assertThat(result).isFalse();
    }




}