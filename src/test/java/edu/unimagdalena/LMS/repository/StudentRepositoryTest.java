package edu.unimagdalena.LMS.repository;

import edu.unimagdalena.LMS.domine.entity.Student;
import edu.unimagdalena.LMS.domine.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StudentRepositoryTest extends AbstractRepositoryIT {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("1. Query Method: Buscar por Email exacto")
    void shouldFindByEmail() {
        // GIVEN
        Student student = Student.builder()
                .fullName("Dani Gs")
                .email("dani@unimagdalena.edu.co")
                .createdAt(Instant.now())
                .build();
        studentRepository.save(student);

        // WHEN
        Optional<Student> found = studentRepository.findByEmail("dani@unimagdalena.edu.co");

        // THEN
        assertThat(found).isPresent();
        assertThat(found.get().getFullName()).isEqualTo("Dani Gs");
    }

    @Test
    @DisplayName("2. Query Method: Buscar por nombre (Ignore Case)")
    void shouldFindByFullNameContainingIgnoreCase() {
        // GIVEN
        studentRepository.save(Student.builder()
                .fullName("Carlos Perez")
                .email("carlos@mail.com")
                .createdAt(Instant.now())
                .build());

        // WHEN
        List<Student> results = studentRepository.findByFullNameContainingIgnoreCase("perez");

        // THEN
        assertThat(results).isNotEmpty();
        assertThat(results.getFirst().getFullName()).isEqualTo("Carlos Perez");
    }

    @Test
    @DisplayName("3. @Query: Buscar estudiantes recientes")
    void shouldFindRecentStudents() {
        // GIVEN: Un estudiante creado hace 2 días y otro hoy
        Instant haceDosDias = Instant.now().minus(2, ChronoUnit.DAYS);
        Instant ahora = Instant.now();

        studentRepository.save(Student.builder()
                .fullName("Estudiante Antiguo")
                .email("viejo@mail.com")
                .createdAt(haceDosDias)
                .build());

        studentRepository.save(Student.builder()
                .fullName("Estudiante Nuevo")
                .email("nuevo@mail.com")
                .createdAt(ahora)
                .build());

        // WHEN: Buscamos estudiantes creados después de ayer (hace 1 día)
        Instant ayer = Instant.now().minus(1, ChronoUnit.DAYS);
        List<Student> recent = studentRepository.findRecentStudents(ayer);

        // THEN: Solo debería encontrar al "Estudiante Nuevo"
        assertThat(recent).hasSize(1);
        assertThat(recent.getFirst().getFullName()).isEqualTo("Estudiante Nuevo");
    }
}