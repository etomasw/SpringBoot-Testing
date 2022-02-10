package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock private StudentRepository repository;
    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService(repository);
    }

    /**
     * We verify if service.getAllStudents()
     * has invoked the findAll() repository method.
     */
    @Test
    void getAllStudents() {
        service.getAllStudents();
        verify(repository).findAll();
    }

    @Test
    void addStudent() {
        // Given
        String email = "jamila@gmail.com";
        Student student = new Student("Jamila",email,Gender.FEMALE);

        // When
        service.addStudent(student);
        
        // Then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(repository).save(studentArgumentCaptor.capture());
        Student captured = studentArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(student);
    }

    @Test
    void addStudentWithEmailTaken() {
        // Given
        String email = "jamila@gmail.com";
        Student student = new Student("Jamila",email,Gender.FEMALE);

        given(repository.selectExistsEmail(student.getEmail()))
                .willReturn(true);

        // When
        assertThatThrownBy(() -> service.addStudent(student))
                .isInstanceOf(BadRequestException.class)
            .hasMessageContaining("Email " + student.getEmail() + " taken");

        // Verify if we execute the save method()
        verify(repository, never()).save(any());
    }

    @Test
    void deleteStudent() {
    }
}