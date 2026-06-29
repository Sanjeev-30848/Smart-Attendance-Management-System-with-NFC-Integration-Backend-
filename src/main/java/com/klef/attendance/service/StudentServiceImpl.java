package com.klef.attendance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.klef.attendance.dto.StudentDTO;
import com.klef.attendance.entity.Student;
import com.klef.attendance.exception.ResourceNotFoundException;
import com.klef.attendance.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentDTO addStudent(StudentDTO dto) {

        Student student = Student.builder()
                .studentName(dto.getStudentName())
                .universityId(dto.getUniversityId())
                .email(dto.getEmail())
                .branch(dto.getBranch())
                .year(dto.getYear())
                .nfcCardUid(dto.getNfcCardUid())
                .silPoints(dto.getSilPoints())
                .build();

        Student savedStudent = studentRepository.save(student);

        return mapToDTO(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found"));

        return mapToDTO(student);
    }

    @Override
    public StudentDTO getStudentByUniversityId(
            String universityId) {

        Student student = studentRepository
                .findByUniversityId(universityId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found"));

        return mapToDTO(student);
    }

    @Override
    public Student getStudentEntityByUniversityId(
            String universityId) {

        return studentRepository
                .findByUniversityId(universityId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found"));
    }

    @Override
    public StudentDTO getStudentByNfcUid(
            String uid) {

        Student student = studentRepository
                .findByNfcCardUid(uid)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Card not registered"));

        return mapToDTO(student);
    }

    @Override
    public StudentDTO updateStudent(
            Long id,
            StudentDTO dto) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found"));

        student.setStudentName(dto.getStudentName());
        student.setUniversityId(dto.getUniversityId());
        student.setEmail(dto.getEmail());
        student.setBranch(dto.getBranch());
        student.setYear(dto.getYear());
        student.setNfcCardUid(dto.getNfcCardUid());
        student.setSilPoints(dto.getSilPoints());

        Student updatedStudent =
                studentRepository.save(student);

        return mapToDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found"));

        studentRepository.delete(student);
    }

    private StudentDTO mapToDTO(Student student) {

        return StudentDTO.builder()
                .id(student.getId())
                .studentName(student.getStudentName())
                .universityId(student.getUniversityId())
                .email(student.getEmail())
                .branch(student.getBranch())
                .year(student.getYear())
                .nfcCardUid(student.getNfcCardUid())
                .silPoints(student.getSilPoints())
                .build();
    }
}