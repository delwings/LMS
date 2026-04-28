package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.request.InstructorProfileRequest;
import edu.unimagdalena.LMS.api.dto.request.InstructorRequest;
import edu.unimagdalena.LMS.api.dto.response.InstructorProfileResponse;
import edu.unimagdalena.LMS.api.dto.response.InstructorResponse;
import edu.unimagdalena.LMS.api.mappers.InstructorMapper;
import edu.unimagdalena.LMS.api.mappers.InstructorProfileMapper;
import edu.unimagdalena.LMS.domine.entity.Instructor;
import edu.unimagdalena.LMS.domine.entity.Instructor_profile;
import edu.unimagdalena.LMS.domine.repository.InstructorProfileRepository;
import edu.unimagdalena.LMS.domine.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {

    @Mock private InstructorRepository instructorRepository;
    @Mock private InstructorProfileRepository profileRepository;
    @Mock private InstructorMapper instructorMapper;
    @Mock private InstructorProfileMapper profileMapper;

    @InjectMocks private InstructorServiceImpl instructorService;

    @Test
    void create_ShouldSaveInstructor() {
        // Given
        InstructorRequest request = new InstructorRequest("Profe Java", "profe@u.com");
        Instructor instructor = Instructor.builder().fullName("Profe Java").build();
        InstructorResponse response = new InstructorResponse(UUID.randomUUID(), "Profe Java", "profe@u.com");

        when(instructorMapper.toEntity(any())).thenReturn(instructor);
        when(instructorRepository.save(any())).thenReturn(instructor);
        when(instructorMapper.toDto(any())).thenReturn(response);

        // When
        InstructorResponse result = instructorService.create(request);

        // Then
        assertThat(result.fullName()).isEqualTo("Profe Java");
        verify(instructorRepository).save(any());
    }

    @Test
    void createProfile_ShouldLinkToInstructor() {
        // Given
        UUID instructorId = UUID.randomUUID();
        InstructorProfileRequest request = new InstructorProfileRequest("Bio", "web.com", "123", instructorId);
        Instructor instructor = Instructor.builder().id(instructorId).build();
        Instructor_profile profile = new Instructor_profile();

        when(instructorRepository.findById(instructorId)).thenReturn(Optional.of(instructor));
        when(profileMapper.toEntity(any(), any())).thenReturn(profile);
        when(profileRepository.save(any())).thenReturn(profile);
        when(profileMapper.toDto(any())).thenReturn(mock(InstructorProfileResponse.class));

        // When
        instructorService.createProfile(request);

        // Then
        verify(profileRepository).save(any());
    }
}