package edu.unimagdalena.LMS.service.impl;

import edu.unimagdalena.LMS.api.dto.request.StudentRequest;
import edu.unimagdalena.LMS.api.dto.response.StudentResponse;
import edu.unimagdalena.LMS.api.error.NotFoundException;
import edu.unimagdalena.LMS.api.mappers.StudentMapper;
import edu.unimagdalena.LMS.domine.entity.Student;
import edu.unimagdalena.LMS.domine.repository.StudentRepository;
import edu.unimagdalena.LMS.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentResponse create(StudentRequest request) {
        Student student = studentMapper.toEntity(request);
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getById(UUID id) {
        return studentRepository.findById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!studentRepository.existsById(id)) {
            throw new NotFoundException("No se puede eliminar: Estudiante no encontrado");
        }
        studentRepository.deleteById(id);
    }
}