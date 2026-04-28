package edu.unimagdalena.LMS.repository;

import edu.unimagdalena.LMS.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    // 1. Buscar estudiante por email exacto
    Optional<Student> findByEmail(String email);

    // 2. Buscar estudiantes cuyo nombre contenga una palabra (LIKE %nombre%)
    List<Student> findByFullNameContainingIgnoreCase(String name);

    // 3. Buscar estudiantes creados después de una fecha específica
    @Query("SELECT s FROM Student s WHERE s.createdAt > :date")
    List<Student> findRecentStudents(Instant date);
}
