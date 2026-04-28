package edu.unimagdalena.LMS.api.dto.response;

import java.util.UUID;

public record InstructorResponse(
        UUID id,
        String fullName,
        String email
) {}