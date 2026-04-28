package edu.unimagdalena.LMS.api.dto.response;

import java.time.Instant;
import java.util.UUID;

public record StudentResponse(
        UUID id,
        String fullName,
        String email,
        Instant createdAt
) {}