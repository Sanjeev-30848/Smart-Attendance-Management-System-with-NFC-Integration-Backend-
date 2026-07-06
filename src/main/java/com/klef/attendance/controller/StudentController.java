package com.klef.attendance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.attendance.dto.StudentDTO;
import com.klef.attendance.service.StudentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "Bearer Authentication")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/university/{universityId}")
    public StudentDTO getStudentByUniversityId(@PathVariable String universityId) {
        return studentService.getStudentByUniversityId(universityId);
    }

    @GetMapping("/nfc/{uid}")
    public StudentDTO getStudentByNfcUid(@PathVariable String uid) {
        return studentService.getStudentByNfcUid(uid);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDTO studentDTO) {

        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "Student Deleted Successfully";
    }
}