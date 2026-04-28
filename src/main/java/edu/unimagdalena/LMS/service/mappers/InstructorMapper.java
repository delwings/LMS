package edu.unimagdalena.LMS.api.mappers;

import edu.unimagdalena.LMS.api.dto.request.InstructorRequest;
import edu.unimagdalena.LMS.api.dto.response.InstructorResponse;
import edu.unimagdalena.LMS.domine.entity.Instructor;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class InstructorMapper {
    public Instructor toEntity(InstructorRequest request) {
        return Instructor.builder()
                .fullName(request.fullName())
                .email(request.email())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public InstructorResponse toDto(Instructor entity) {
        return new InstructorResponse(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail()
        );
    }
}