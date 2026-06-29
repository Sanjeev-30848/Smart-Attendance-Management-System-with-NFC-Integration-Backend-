package com.klef.attendance.service;

import java.util.List;

import com.klef.attendance.dto.StudentDTO;
import com.klef.attendance.entity.Student;

public interface StudentService {

    StudentDTO addStudent(StudentDTO dto);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO getStudentByUniversityId(
            String universityId);

    Student getStudentEntityByUniversityId(
            String universityId);

    StudentDTO getStudentByNfcUid(String uid);

    StudentDTO updateStudent(
            Long id,
            StudentDTO dto);

    void deleteStudent(Long id);
}