package edu.unimagdalena.LMS.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record LessonRequest(
        @NotBlank(message = "El título de la lección es obligatorio")
        String title,

        String content,

        @Min(value = 0, message = "El índice de orden debe ser positivo")
        int orderIndex,

        @NotNull(message = "La lección debe pertenecer a un curso")
        UUID courseId
) {}