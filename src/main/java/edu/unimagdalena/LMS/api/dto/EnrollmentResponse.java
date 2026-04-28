package edu.unimagdalena.LMS.api.dto.response;

import java.time.Instant;
import java.util.UUID;

public record EnrollmentResponse(
        UUID id,
        UUID studentId,
        UUID courseId,
        String status,
        Instant enrolledAt
) {}