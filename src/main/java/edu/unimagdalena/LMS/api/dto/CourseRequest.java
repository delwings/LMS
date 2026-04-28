package edu.unimagdalena.LMS.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CourseRequest(
        @NotBlank(message = "El título es obligatorio")
        String title,

        @NotNull(message = "Debe asignar un instructor")
        UUID instructorId,

        String status,

        boolean active
) {}