package edu.unimagdalena.LMS.repository;

import edu.unimagdalena.LMS.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, UUID> {
    // Query Method: Buscar por email
    Optional<Instructor> findByEmail(String email);
}