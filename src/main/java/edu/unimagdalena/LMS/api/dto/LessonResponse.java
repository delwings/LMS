package edu.unimagdalena.LMS.api.dto.response;

import java.util.UUID;

public record LessonResponse(
        UUID id,
        String title,
        String content,
        int orderIndex,
        UUID courseId
) {}