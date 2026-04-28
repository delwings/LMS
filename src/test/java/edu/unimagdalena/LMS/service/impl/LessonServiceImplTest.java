package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.mappers.LessonMapper;
import edu.unimagdalena.LMS.domine.entity.Lesson;
import edu.unimagdalena.LMS.domine.repository.LessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessonServiceImplTest {

    @Mock private LessonRepository lessonRepository;
    @Mock private LessonMapper lessonMapper;

    @InjectMocks private LessonServiceImpl lessonService;

    @Test
    void findByCourseOrdered_ShouldReturnList() {
        // Given
        UUID courseId = UUID.randomUUID();
        when(lessonRepository.findByCourseIdOrderByOrderIndexAsc(courseId)).thenReturn(List.of(new Lesson()));

        // When
        var result = lessonService.findByCourseOrdered(courseId);

        // Then
        assertThat(result).hasSize(1);
    }
}