package edu.unimagdalena.LMS.api.dto.response;

import java.time.Instant;
import java.util.UUID;

public record AssessmentResponse(
        UUID id,
        String type,
        Integer score,
        Instant takenAt,
        UUID studentId,
        UUID courseId
) {}