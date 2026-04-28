package edu.unimagdalena.LMS.domine.repository;

import edu.unimagdalena.LMS.domine.entity.Instructor_profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstructorProfileRepository extends JpaRepository<Instructor_profile, UUID> {
    //
}