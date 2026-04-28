package edu.unimagdalena.LMS.repository;

import edu.unimagdalena.LMS.domine.entity.Instructor;
import edu.unimagdalena.LMS.domine.repository.InstructorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class InstructorRepositoryTest extends AbstractRepositoryIT {

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    @DisplayName("1. Query Method: Buscar Instructor por Email exacto")
    void shouldFindByEmail() {
        // GIVEN: Usamos el Builder con tus campos reales
        Instructor instructor = Instructor.builder()
                .fullName("Prof. Dani Gs")
                .email("danigs_prof@unimag.edu.co")
                .createdAt(Instant.now())
                .build();

        instructorRepository.save(instructor);

        // WHEN: Ejecutamos la búsqueda
        Optional<Instructor> found = instructorRepository.findByEmail("danigs_prof@unimag.edu.co");

        // THEN: Verificamos que el Optional contenga al instructor correcto
        assertThat(found).isPresent();
        assertThat(found.get().getFullName()).isEqualTo("Prof. Dani Gs");
    }

    @Test
    @DisplayName("2. CRUD: Verificar persistencia de UUID")
    void shouldPersistWithGeneratedUUID() {
        // GIVEN
        Instructor instructor = Instructor.builder()
                .fullName("Test UUID")
                .email("uuid@test.com")
                .build();

        // WHEN
        Instructor guardado = instructorRepository.save(instructor);

        // THEN: Validamos que Hibernate generó el UUID automáticamente
        assertThat(guardado.getId()).isNotNull();
    }
}