package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.request.StudentRequest;
import edu.unimagdalena.LMS.api.dto.response.StudentResponse;
import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.api.mappers.StudentMapper;
import edu.unimagdalena.LMS.domine.entity.Student;
import edu.unimagdalena.LMS.domine.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentResponse studentResponse;
    private UUID studentId;

    @BeforeEach
    void setUp() {
        studentId = UUID.randomUUID();
        student = Student.builder()
                .id(studentId)
                .fullName("Gabriel Garcia")
                .email("gabriel@macondo.com")
                .createdAt(Instant.now())
                .build();

        studentResponse = new StudentResponse(
                studentId,
                "Gabriel Garcia",
                "gabriel@macondo.com",
                student.getCreatedAt()
        );
    }

    @Test
    void create_ShouldReturnResponse_WhenValidRequest() {
        // Given
        StudentRequest request = new StudentRequest("Gabriel Garcia", "gabriel@macondo.com");
        when(studentMapper.toEntity(any())).thenReturn(student);
        when(studentRepository.save(any())).thenReturn(student);
        when(studentMapper.toDto(any())).thenReturn(studentResponse);

        // When
        StudentResponse result = studentService.create(request);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(studentId);
        verify(studentRepository).save(any());
    }

    @Test
    void getById_ShouldReturnResponse_WhenStudentExists() {
        // Given
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toDto(student)).thenReturn(studentResponse);

        // When
        StudentResponse result = studentService.getById(studentId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.fullName()).isEqualTo("Gabriel Garcia");
    }

    @Test
    void getById_ShouldThrowNotFoundException_WhenStudentDoesNotExist() {
        // Given
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> studentService.getById(studentId))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Estudiante no encontrado");
    }

    @Test
    void findAll_ShouldReturnList() {
        // Given
        when(studentRepository.findAll()).thenReturn(List.of(student));
        when(studentMapper.toDto(any())).thenReturn(studentResponse);

        // When
        List<StudentResponse> result = studentService.findAll();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).email()).isEqualTo("gabriel@macondo.com");
    }

    @Test
    void delete_ShouldCallRepository_WhenStudentExists() {
        // Given
        when(studentRepository.existsById(studentId)).thenReturn(true);

        // When
        studentService.delete(studentId);

        // Then
        verify(studentRepository).deleteById(studentId);
    }

    @Test
    void delete_ShouldThrowException_WhenStudentDoesNotExist() {
        // Given
        when(studentRepository.existsById(studentId)).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> studentService.delete(studentId))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("No se puede eliminar: Estudiante no encontrado");

        verify(studentRepository, never()).deleteById(any());
    }
}