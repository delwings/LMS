package edu.unimagdalena.LMS.repository;

import edu.unimagdalena.LMS.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    // Query Method: Cursos activos para el catálogo
    List<Course> findByActiveTrue();

    // Query Method: Cursos de un instructor específico (usando el objeto Instructor)
    List<Course> findByInstructorId(UUID instructorId);

    // @Query: Cursos con más de X lecciones (usando JPQL)
    @Query("SELECT c FROM Course c WHERE size(c.lessons) > :minLessons")
    List<Course> findPopularCourses(int minLessons);
}