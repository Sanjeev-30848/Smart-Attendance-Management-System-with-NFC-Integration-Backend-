package com.klef.attendance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.attendance.entity.Attendance;
import com.klef.attendance.entity.Event;
import com.klef.attendance.entity.Student;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByStudentAndEvent(
            Student student,
            Event event);

    List<Attendance> findByStudent(
            Student student);

    List<Attendance> findByEvent(
            Event event);
}