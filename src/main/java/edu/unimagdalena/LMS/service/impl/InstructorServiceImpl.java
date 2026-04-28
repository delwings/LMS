package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.request.InstructorRequest;
import edu.unimagdalena.LMS.api.dto.request.InstructorProfileRequest;
import edu.unimagdalena.LMS.api.dto.response.InstructorResponse;
import edu.unimagdalena.LMS.api.dto.response.InstructorProfileResponse;
import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.api.mappers.InstructorMapper;
import edu.unimagdalena.LMS.api.mappers.InstructorProfileMapper;
import edu.unimagdalena.LMS.domine.entity.Instructor;
import edu.unimagdalena.LMS.domine.entity.Instructor_profile;
import edu.unimagdalena.LMS.domine.repository.InstructorRepository;
import edu.unimagdalena.LMS.domine.repository.InstructorProfileRepository; // Asegúrate de tener este repo
import edu.unimagdalena.LMS.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorProfileRepository profileRepository;
    private final InstructorMapper instructorMapper;
    private final InstructorProfileMapper profileMapper;

    @Override
    @Transactional
    public InstructorResponse create(InstructorRequest request) {
        Instructor instructor = instructorMapper.toEntity(request);
        return instructorMapper.toDto(instructorRepository.save(instructor));
    }

    @Override
    @Transactional
    public InstructorProfileResponse createProfile(InstructorProfileRequest request) {
        Instructor instructor = instructorRepository.findById(request.instructorId())
                .orElseThrow(() -> new NotFoundException("Instructor no encontrado para crear perfil"));

        Instructor_profile profile = profileMapper.toEntity(request, instructor);
        return profileMapper.toDto(profileRepository.save(profile));
    }

    @Override
    @Transactional(readOnly = true)
    public InstructorResponse getById(UUID id) {
        return instructorRepository.findById(id)
                .map(instructorMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Instructor no encontrado"));
    }
}