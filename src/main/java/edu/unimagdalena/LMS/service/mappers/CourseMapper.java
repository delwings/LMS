package edu.unimagdalena.LMS.api.mappers;

import edu.unimagdalena.LMS.api.dto.request.CourseRequest;
import edu.unimagdalena.LMS.api.dto.response.CourseResponse;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Instructor;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class CourseMapper {
    public Course toEntity(CourseRequest request, Instructor instructor) {
        return Course.builder()
                .title(request.title())
                .status(request.status())
                .active(request.active())
                .instructor(instructor)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public CourseResponse toDto(Course entity) {
        return new CourseResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getStatus(),
                entity.getActive(),
                entity.getInstructor().getId()
        );
    }
}