package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.request.CourseRequest;
import edu.unimagdalena.LMS.api.dto.response.CourseResponse;
import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.api.mappers.CourseMapper;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Instructor;
import edu.unimagdalena.LMS.domine.repository.CourseRepository;
import edu.unimagdalena.LMS.domine.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock private CourseRepository courseRepository;
    @Mock private InstructorRepository instructorRepository;
    @Mock private CourseMapper courseMapper;

    @InjectMocks private CourseServiceImpl courseService;

    @Test
    void create_ShouldReturnResponse_WhenInstructorExists() {
        // Given
        UUID instructorId = UUID.randomUUID();
        CourseRequest request = new CourseRequest("Java Unit Testing", instructorId, "DRAFT", true);
        Instructor instructor = Instructor.builder().id(instructorId).build();
        Course course = Course.builder().title("Java Unit Testing").build();
        CourseResponse response = new CourseResponse(UUID.randomUUID(), "Java Unit Testing", "DRAFT", true, instructorId);

        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));
        when(courseMapper.toEntity(any(), any())).thenReturn(course);
        when(courseRepository.save(any())).thenReturn(course);
        when(courseMapper.toDto(any())).thenReturn(response);

        // When
        CourseResponse result = courseService.create(request);

        // Then
        assertThat(result).isNotNull();
        verify(courseRepository).save(any());
    }

    @Test
    void findActiveCourses_ShouldReturnList() {
        // Given
        when(courseRepository.findByActiveTrue()).thenReturn(List.of(new Course()));
        when(courseMapper.toDto(any())).thenReturn(mock(CourseResponse.class));

        // When
        List<CourseResponse> result = courseService.findActiveCourses();

        // Then
        assertThat(result).hasSize(1);
        verify(courseRepository).findByActiveTrue();
    }
}