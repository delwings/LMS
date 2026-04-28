package edu.unimagdalena.LMS.repository;

import edu.unimagdalena.LMS.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, UUID> {

    // @Query: Promedio de notas de un estudiante
    @Query("SELECT AVG(a.score) FROM Assessment a WHERE a.student.id = :studentId")
    Double getAverageScoreByStudent(UUID studentId);
}