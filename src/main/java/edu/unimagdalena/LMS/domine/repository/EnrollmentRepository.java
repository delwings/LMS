package edu.unimagdalena.LMS.domine.repository;

import edu.unimagdalena.LMS.domine.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {

    // Query Method: Ver todas las inscripciones de un estudiante
    List<Enrollment> findByStudentId(UUID studentId);

    // Query Method: Ver inscripciones por estado (ej: 'COMPLETED', 'ACTIVE')
    List<Enrollment> findByStatus(String status);
}