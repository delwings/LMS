package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.response.AssessmentResponse;
import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.api.mappers.AssessmentMapper;
import edu.unimagdalena.LMS.domine.repository.AssessmentRepository;
import edu.unimagdalena.LMS.domine.repository.StudentRepository;
import edu.unimagdalena.LMS.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public Double getAverageByStudent(UUID studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new NotFoundException("Estudiante no encontrado");
        }

        Double average = assessmentRepository.getAverageScoreByStudent(studentId);
        return (average != null) ? average : 0.0;
    }
}