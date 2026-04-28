package edu.unimagdalena.LMS.service;

import edu.unimagdalena.LMS.api.dto.response.AssessmentResponse;
import java.util.UUID;

public interface AssessmentService {
    Double getAverageByStudent(UUID studentId); // Requerimiento: Promedio de notas
}