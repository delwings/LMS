package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.request.EnrollmentRequest;
import edu.unimagdalena.LMS.api.dto.response.EnrollmentResponse;
import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.api.mappers.EnrollmentMapper;
import edu.unimagdalena.LMS.domine.entity.Course;
import edu.unimagdalena.LMS.domine.entity.Enrollment;
import edu.unimagdalena.LMS.domine.entity.Student;
import edu.unimagdalena.LMS.domine.repository.CourseRepository;
import edu.unimagdalena.LMS.domine.repository.EnrollmentRepository;
import edu.unimagdalena.LMS.domine.repository.StudentRepository;
import edu.unimagdalena.LMS.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    @Transactional
    public EnrollmentResponse enroll(EnrollmentRequest request) {
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));

        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        Enrollment enrollment = enrollmentMapper.toEntity(request, student, course);
        return enrollmentMapper.toDto(enrollmentRepository.save(enrollment));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getByStudent(UUID studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(enrollmentMapper::toDto)
                .toList();
    }
}