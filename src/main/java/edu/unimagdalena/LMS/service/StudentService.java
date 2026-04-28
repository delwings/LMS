package edu.unimagdalena.LMS.service;

import edu.unimagdalena.LMS.api.dto.request.StudentRequest;
import edu.unimagdalena.LMS.api.dto.response.StudentResponse;
import java.util.List;
import java.util.UUID;

public interface StudentService {
    StudentResponse create(StudentRequest request);
    StudentResponse getById(UUID id);
    List<StudentResponse> findAll();
    void delete(UUID id);
}