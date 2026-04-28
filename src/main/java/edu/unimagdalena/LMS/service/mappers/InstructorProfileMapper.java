package edu.unimagdalena.LMS.api.mappers;

import edu.unimagdalena.LMS.api.dto.request.InstructorProfileRequest;
import edu.unimagdalena.LMS.api.dto.response.InstructorProfileResponse;
import edu.unimagdalena.LMS.domine.entity.Instructor;
import edu.unimagdalena.LMS.domine.entity.Instructor_profile;
import org.springframework.stereotype.Component;

@Component
public class InstructorProfileMapper {

    public Instructor_profile toEntity(InstructorProfileRequest request, Instructor instructor) {
        return Instructor_profile.builder()
                .bio(request.bio())
                .phone(request.phone())
                .instructor(instructor)
                .build();
    }

    public InstructorProfileResponse toDto(Instructor_profile entity) {
        return new InstructorProfileResponse(
                entity.getId(),
                entity.getBio(),
                entity.getPhone(),
                entity.getInstructor().getId()
        );
    }
}