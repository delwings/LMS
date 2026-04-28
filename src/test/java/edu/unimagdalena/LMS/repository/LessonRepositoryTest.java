package edu.unimagdalena.LMS.repository;

import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Lesson;
import edu.unimagdalena.LMS.domine.repository.CourseRepository;
import edu.unimagdalena.LMS.domine.repository.LessonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LessonRepositoryTest extends AbstractRepositoryIT {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @DisplayName("1. Query Method: Obtener lecciones de un curso ordenadas")
    void shouldFindByCourseIdOrderByOrderIndexAsc() {
        // GIVEN: Creamos un curso
        Course web = courseRepository.save(Course.builder()
                .title("Programación Web")
                .build());

        // GIVEN: Creamos tres lecciones en desorden de inserción
        lessonRepository.save(Lesson.builder()
                .title("Introducción a JPA")
                .orderIndex(2)
                .course(web)
                .build());

        lessonRepository.save(Lesson.builder()
                .title("Configuración de Spring")
                .orderIndex(1)
                .course(web)
                .build());

        // WHEN: Buscamos las lecciones de ese curso
        List<Lesson> lessons = lessonRepository.findByCourseIdOrderByOrderIndexAsc(web.getId());

        // THEN: Verificamos que trajo 2 y que la primera es la que tiene índice 1
        assertThat(lessons).hasSize(2);
        assertThat(lessons.get(0).getOrderIndex()).isEqualTo(1);
        assertThat(lessons.get(0).getTitle()).isEqualTo("Configuración de Spring");
        assertThat(lessons.get(1).getOrderIndex()).isEqualTo(2);
    }
}