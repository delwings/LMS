package edu.unimagdalena.LMS.api.mappers;

import edu.unimagdalena.LMS.api.dto.request.LessonRequest;
import edu.unimagdalena.LMS.api.dto.response.LessonResponse;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public Lesson toEntity(LessonRequest request, Course course) {
        return Lesson.builder()
                .title(request.title())
                .orderIndex(request.orderIndex())
                .course(course)
                .build();
    }

    public LessonResponse toDto(Lesson entity) {
        return new LessonResponse(
                entity.getId(),
                entity.getTitle(),
                "N/A", // Entidad Lesson no tiene campo 'content'
                entity.getOrderIndex(),
                entity.getCourse().getId()
        );
    }
}