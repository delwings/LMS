package edu.unimagdalena.LMS.api.mappers;

import edu.unimagdalena.LMS.api.dto.request.AssessmentRequest;
import edu.unimagdalena.LMS.api.dto.response.AssessmentResponse;
import edu.unimagdalena.LMS.domine.entity.Assessment;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Student;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class AssessmentMapper {
    public Assessment toEntity(AssessmentRequest request, Student student, Course course) {
        return Assessment.builder()
                .type(request.type())
                .score(request.score())
                .student(student)
                .course(course)
                .takenAt(Instant.now())
                .build();
    }

    public AssessmentResponse toDto(Assessment entity) {
        return new AssessmentResponse(
                entity.getId(),
                entity.getType(),
                entity.getScore(),
                entity.getTakenAt(),
                entity.getStudent().getId(),
                entity.getCourse().getId()
        );
    }
}