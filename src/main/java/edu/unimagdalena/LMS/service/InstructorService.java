package edu.unimagdalena.LMS.service;

import edu.unimagdalena.LMS.api.dto.request.InstructorRequest;
import edu.unimagdalena.LMS.api.dto.request.InstructorProfileRequest;
import edu.unimagdalena.LMS.api.dto.response.InstructorResponse;
import edu.unimagdalena.LMS.api.dto.response.InstructorProfileResponse;
import java.util.UUID;

public interface InstructorService {
    InstructorResponse create(InstructorRequest request);
    InstructorProfileResponse createProfile(InstructorProfileRequest request);
    InstructorResponse getById(UUID id);
}