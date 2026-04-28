package edu.unimagdalena.LMS.repository;

import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Enrollment;
import edu.unimagdalena.LMS.domine.entity.Student;
import edu.unimagdalena.LMS.domine.repository.CourseRepository;
import edu.unimagdalena.LMS.domine.repository.EnrollmentRepository;
import edu.unimagdalena.LMS.domine.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EnrollmentRepositoryTest extends AbstractRepositoryIT {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @DisplayName("1. Query Method: Buscar inscripciones por ID de Estudiante")
    void shouldFindByStudentId() {
        // GIVEN: Creamos los datos necesarios
        Student student = studentRepository.save(Student.builder()
                .fullName("Dani Gs")
                .email("dani@mail.com")
                .build());

        Course course = courseRepository.save(Course.builder()
                .title("Programación III")
                .build());

        enrollmentRepository.save(Enrollment.builder()
                .student(student)
                .course(course)
                .status("ACTIVE")
                .enrolledAt(Instant.now())
                .build());

        // WHEN: Buscamos por el ID del estudiante guardado
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(student.getId());

        // THEN
        assertThat(enrollments).hasSize(1);
        assertThat(enrollments.getFirst().getStudent().getFullName()).isEqualTo("Dani Gs");
        assertThat(enrollments.getFirst().getCourse().getTitle()).isEqualTo("Programación III");
    }

    @Test
    @DisplayName("2. Query Method: Buscar por estado de inscripción")
    void shouldFindByStatus() {
        // GIVEN: Creamos dos inscripciones con estados diferentes
        enrollmentRepository.save(Enrollment.builder()
                .status("COMPLETED")
                .enrolledAt(Instant.now())
                .build());

        enrollmentRepository.save(Enrollment.builder()
                .status("ACTIVE")
                .enrolledAt(Instant.now())
                .build());

        // WHEN: Buscamos solo las completadas
        List<Enrollment> completed = enrollmentRepository.findByStatus("COMPLETED");

        // THEN
        assertThat(completed).hasSize(1);
        assertThat(completed.getFirst().getStatus()).isEqualTo("COMPLETED");
    }
}