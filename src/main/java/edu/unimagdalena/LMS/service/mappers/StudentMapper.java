package edu.unimagdalena.LMS.api.mappers;

import edu.unimagdalena.LMS.api.dto.request.StudentRequest;
import edu.unimagdalena.LMS.api.dto.response.StudentResponse;
import edu.unimagdalena.LMS.domine.entity.Student;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class StudentMapper {
    public Student toEntity(StudentRequest request) {
        return Student.builder()
                .fullName(request.fullName())
                .email(request.email())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public StudentResponse toDto(Student entity) {
        return new StudentResponse(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getCreatedAt()
        );
    }
}