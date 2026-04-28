package edu.unimagdalena.LMS.service;

import edu.unimagdalena.LMS.api.dto.request.EnrollmentRequest;
import edu.unimagdalena.LMS.api.dto.response.EnrollmentResponse;
import java.util.List;
import java.util.UUID;

public interface EnrollmentService {
    EnrollmentResponse enroll(EnrollmentRequest request);
    List<EnrollmentResponse> getByStudent(UUID studentId);
}