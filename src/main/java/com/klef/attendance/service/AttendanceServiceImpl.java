package com.klef.attendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klef.attendance.entity.Attendance;
import com.klef.attendance.entity.Event;
import com.klef.attendance.entity.Student;
import com.klef.attendance.exception.DuplicateAttendanceException;
import com.klef.attendance.exception.ResourceNotFoundException;
import com.klef.attendance.repository.AttendanceRepository;
import com.klef.attendance.repository.EventRepository;
import com.klef.attendance.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl
        implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final EventRepository eventRepository;

    @Override
    public Attendance markAttendance(
            String universityId,
            Long eventId) {

        Student student =
                studentRepository
                        .findByUniversityId(universityId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found"));

        Event event =
                eventRepository.findById(eventId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Event not found"));

        if (attendanceRepository
                .findByStudentAndEvent(student, event)
                .isPresent()) {

            throw new DuplicateAttendanceException(
                    "Attendance already marked");
        }

        Attendance attendance =
                Attendance.builder()
                        .student(student)
                        .event(event)
                        .build();

        student.setSilPoints(
                student.getSilPoints()
                        + event.getSilPointsAwarded());

        studentRepository.save(student);

        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getAttendanceByStudent(
            String universityId) {

        Student student =
                studentRepository
                        .findByUniversityId(universityId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found"));

        return attendanceRepository.findByStudent(student);
    }

    @Override
    public List<Attendance> getAttendanceByEvent(
            Long eventId) {

        Event event =
                eventRepository.findById(eventId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Event not found"));

        return attendanceRepository.findByEvent(event);
    }
}