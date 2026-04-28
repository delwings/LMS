package edu.unimagdalena.LMS.api.dto.response;

import java.util.UUID;

public record CourseResponse(
        UUID id,
        String title,
        String status,
        boolean active,
        UUID instructorId
) {}