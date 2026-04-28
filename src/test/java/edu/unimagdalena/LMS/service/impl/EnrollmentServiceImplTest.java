package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.request.EnrollmentRequest;
import edu.unimagdalena.LMS.api.dto.response.EnrollmentResponse;
import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.api.mappers.EnrollmentMapper;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Enrollment;
import edu.unimagdalena.LMS.domine.entity.Student;
import edu.unimagdalena.LMS.domine.repository.CourseRepository;
import edu.unimagdalena.LMS.domine.repository.EnrollmentRepository;
import edu.unimagdalena.LMS.domine.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceImplTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private EnrollmentMapper enrollmentMapper;

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    private Student student;
    private Course course;
    private EnrollmentRequest request;

    @BeforeEach
    void setUp() {
        student = Student.builder().id(UUID.randomUUID()).fullName("Estudiante Test").build();
        course = Course.builder().id(UUID.randomUUID()).title("Curso Test").build();
        request = new EnrollmentRequest(student.getId(), course.getId());
    }

    @Test
    void enroll_ShouldReturnResponse_WhenValidRequest() {
        // Given
        Enrollment enrollment = Enrollment.builder().id(UUID.randomUUID()).student(student).course(course).build();
        EnrollmentResponse response = new EnrollmentResponse(enrollment.getId(), student.getId(), course.getId(), "ACTIVE", Instant.now());

        when(studentRepository.findById(request.studentId())).thenReturn(Optional.of(student));
        when(courseRepository.findById(request.courseId())).thenReturn(Optional.of(course));
        when(enrollmentMapper.toEntity(any(), any(), any())).thenReturn(enrollment);
        when(enrollmentRepository.save(any())).thenReturn(enrollment);
        when(enrollmentMapper.toDto(any())).thenReturn(response);

        // When
        EnrollmentResponse result = enrollmentService.enroll(request);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.studentId()).isEqualTo(student.getId());
        verify(enrollmentRepository).save(any());
    }

    @Test
    void enroll_ShouldThrowNotFoundException_WhenStudentNotFound() {
        // Given
        when(studentRepository.findById(any())).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> enrollmentService.enroll(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Estudiante no encontrado");

        verify(enrollmentRepository, never()).save(any());
    }
}