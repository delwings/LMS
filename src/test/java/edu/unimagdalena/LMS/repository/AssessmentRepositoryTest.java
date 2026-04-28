package edu.unimagdalena.LMS.repository;

import edu.unimagdalena.LMS.domine.entity.Assessment;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Student;
import edu.unimagdalena.LMS.domine.repository.AssessmentRepository;
import edu.unimagdalena.LMS.domine.repository.CourseRepository;
import edu.unimagdalena.LMS.domine.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class AssessmentRepositoryTest extends AbstractRepositoryIT {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @DisplayName("1. @Query: Calcular promedio de notas de un estudiante")
    void shouldGetAverageScoreByStudent() {
        // GIVEN: Creamos un estudiante y un curso
        Student dani = studentRepository.save(Student.builder()
                .fullName("Dani Gs")
                .email("dani@mail.com")
                .build());

        Course programacion = courseRepository.save(Course.builder()
                .title("Programación Web")
                .active(true)
                .build());

        // GIVEN: Le ponemos dos notas: un 10 y un 20 (Promedio esperado: 15.0)
        assessmentRepository.save(Assessment.builder()
                .student(dani)
                .course(programacion)
                .score(10)
                .type("Parcial 1")
                .takenAt(Instant.now())
                .build());

        assessmentRepository.save(Assessment.builder()
                .student(dani)
                .course(programacion)
                .score(20)
                .type("Parcial 2")
                .takenAt(Instant.now())
                .build());

        // WHEN: Ejecutamos tu @Query
        Double promedio = assessmentRepository.getAverageScoreByStudent(dani.getId());

        // THEN: Verificamos el cálculo
        assertThat(promedio).isNotNull();
        assertThat(promedio).isEqualTo(15.0);
    }

    @Test
    @DisplayName("2. @Query: Promedio de un estudiante sin notas")
    void shouldReturnNullWhenNoScores() {
        // GIVEN: Estudiante sin assessments
        Student solo = studentRepository.save(Student.builder()
                .fullName("Solo")
                .email("solo@mail.com")
                .build());

        // WHEN
        Double promedio = assessmentRepository.getAverageScoreByStudent(solo.getId());

        // THEN: La función AVG de SQL devuelve null si no hay filas
        assertThat(promedio).isNull();
    }
}