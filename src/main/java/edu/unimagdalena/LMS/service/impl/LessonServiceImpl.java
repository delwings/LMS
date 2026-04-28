package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.request.LessonRequest;
import edu.unimagdalena.LMS.api.dto.response.LessonResponse;
import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.api.mappers.LessonMapper;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Lesson;
import edu.unimagdalena.LMS.domine.repository.CourseRepository;
import edu.unimagdalena.LMS.domine.repository.LessonRepository;
import edu.unimagdalena.LMS.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;

    @Override
    @Transactional
    public LessonResponse create(LessonRequest request) {
        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new NotFoundException("Curso no encontrado para la lección"));

        Lesson lesson = lessonMapper.toEntity(request, course);
        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonResponse> findByCourseOrdered(UUID courseId) {
        return lessonRepository.findByCourseIdOrderByOrderIndexAsc(courseId).stream()
                .map(lessonMapper::toDto).toList();
    }
}