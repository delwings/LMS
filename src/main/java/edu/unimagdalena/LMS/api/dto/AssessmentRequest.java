package edu.unimagdalena.LMS.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AssessmentRequest(
        @NotBlank(message = "El tipo de evaluación es obligatorio (ej. EXAM, QUIZ)")
        String type,

        @Min(value = 0, message = "La nota mínima es 0")
        @Max(value = 100, message = "La nota máxima es 100")
        Integer score,

        @NotNull(message = "Debe asignar un estudiante")
        UUID studentId,

        @NotNull(message = "Debe asignar un curso")
        UUID courseId
) {}