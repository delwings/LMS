package edu.unimagdalena.LMS.api.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record EnrollmentRequest(
        @NotNull(message = "El ID del estudiante es obligatorio")
        UUID studentId,

        @NotNull(message = "El ID del curso es obligatorio")
        UUID courseId
) {}