package edu.unimagdalena.LMS.service;

import edu.unimagdalena.LMS.api.dto.request.CourseRequest;
import edu.unimagdalena.LMS.api.dto.response.CourseResponse;
import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseResponse create(CourseRequest request);
    List<CourseResponse> findActiveCourses(); // Requerimiento: Cursos activos
    List<CourseResponse> findPopularCourses(int minLessons); // Requerimiento: Cursos con > X lecciones
    CourseResponse getById(UUID id);
}