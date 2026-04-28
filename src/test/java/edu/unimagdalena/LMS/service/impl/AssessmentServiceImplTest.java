package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.domine.repository.AssessmentRepository;
import edu.unimagdalena.LMS.domine.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceImplTest {

    @Mock
    private AssessmentRepository assessmentRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private AssessmentServiceImpl assessmentService;

    @Test
    void getAverageByStudent_ShouldReturnAverage_WhenStudentExists() {
        // Given
        UUID studentId = UUID.randomUUID();
        Double expectedAverage = 85.5;

        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(assessmentRepository.getAverageScoreByStudent(studentId)).thenReturn(expectedAverage);

        // When
        Double result = assessmentService.getAverageByStudent(studentId);

        // Then
        assertThat(result).isEqualTo(expectedAverage);
    }

    @Test
    void getAverageByStudent_ShouldReturnZero_WhenNoAssessments() {
        // Given
        UUID studentId = UUID.randomUUID();
        when(studentRepository.existsById(studentId)).thenReturn(true);
        when(assessmentRepository.getAverageScoreByStudent(studentId)).thenReturn(null);

        // When
        Double result = assessmentService.getAverageByStudent(studentId);

        // Then
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void getAverageByStudent_ShouldThrowNotFoundException_WhenStudentDoesNotExist() {
        // Given
        UUID studentId = UUID.randomUUID();
        when(studentRepository.existsById(studentId)).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> assessmentService.getAverageByStudent(studentId))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Estudiante no encontrado");
    }
}