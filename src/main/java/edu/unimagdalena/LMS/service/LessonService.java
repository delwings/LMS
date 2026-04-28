package edu.unimagdalena.LMS.service;

import edu.unimagdalena.LMS.api.dto.request.LessonRequest;
import edu.unimagdalena.LMS.api.dto.response.LessonResponse;
import java.util.List;
import java.util.UUID;

public interface LessonService {
    LessonResponse create(LessonRequest request);
    List<LessonResponse> findByCourseOrdered(UUID courseId); // Requerimiento: Lecciones ordenadas
}