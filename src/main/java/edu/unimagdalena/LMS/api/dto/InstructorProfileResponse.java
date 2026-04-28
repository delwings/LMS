package edu.unimagdalena.LMS.api.dto.response;

import java.util.UUID;

public record InstructorProfileResponse(
        UUID id,
        String bio,
        String phone,
        UUID instructorId
) {}