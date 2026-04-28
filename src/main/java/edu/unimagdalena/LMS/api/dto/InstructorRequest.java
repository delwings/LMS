package edu.unimagdalena.LMS.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InstructorRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String fullName,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Email no válido")
        String email
) {}