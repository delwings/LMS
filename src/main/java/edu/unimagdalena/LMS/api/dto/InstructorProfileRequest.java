package edu.unimagdalena.LMS.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record InstructorProfileRequest(
        @NotBlank(message = "La biografía no puede estar vacía")
        String bio,

        String website,

        @NotBlank(message = "La especialidad es obligatoria")
        String phone,

        UUID instructorId
) {}
