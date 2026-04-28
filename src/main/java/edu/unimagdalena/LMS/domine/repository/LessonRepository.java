package edu.unimagdalena.LMS.domine.repository;

import edu.unimagdalena.LMS.domine.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    // Query Method: Obtener las lecciones de un curso ordenadas por su índice
    List<Lesson> findByCourseIdOrderByOrderIndexAsc(UUID courseId);
}
