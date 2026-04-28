package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.request.CourseRequest;
import edu.unimagdalena.LMS.api.dto.response.CourseResponse;
import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.api.mappers.CourseMapper;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Instructor;
import edu.unimagdalena.LMS.domine.repository.CourseRepository;
import edu.unimagdalena.LMS.domine.repository.InstructorRepository;
import edu.unimagdalena.LMS.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CourseMapper courseMapper;

    @Override
    @Transactional
    public CourseResponse create(CourseRequest request) {
        Instructor instructor = instructorRepository.findById(request.instructorId())
                .orElseThrow(() -> new NotFoundException("Instructor no encontrado"));

        Course course = courseMapper.toEntity(request, instructor);
        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> findActiveCourses() {
        return courseRepository.findByActiveTrue().stream()
                .map(courseMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> findPopularCourses(int minLessons) {
        return courseRepository.findPopularCourses(minLessons).stream()
                .map(courseMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getById(UUID id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));
    }
}