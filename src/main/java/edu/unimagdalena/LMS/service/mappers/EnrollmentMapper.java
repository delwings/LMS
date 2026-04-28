package edu.unimagdalena.LMS.api.mappers;

import edu.unimagdalena.LMS.api.dto.request.EnrollmentRequest;
import edu.unimagdalena.LMS.api.dto.response.EnrollmentResponse;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Enrollment;
import edu.unimagdalena.LMS.domine.entity.Student;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class EnrollmentMapper {
    public Enrollment toEntity(EnrollmentRequest request, Student student, Course course) {
        return Enrollment.builder()
                .student(student)
                .course(course)
                .status("ACTIVE")
                .enrolledAt(Instant.now())
                .build();
    }

    public EnrollmentResponse toDto(Enrollment entity) {
        return new EnrollmentResponse(
                entity.getId(),
                entity.getStudent().getId(),
                entity.getCourse().getId(),
                entity.getStatus(),
                entity.getEnrolledAt()
        );
    }
}