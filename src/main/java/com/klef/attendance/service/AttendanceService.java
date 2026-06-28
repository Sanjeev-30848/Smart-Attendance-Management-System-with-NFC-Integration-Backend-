package com.klef.attendance.service;

import java.util.List;

import com.klef.attendance.entity.Attendance;

public interface AttendanceService {

    Attendance markAttendance(
            String universityId,
            Long eventId);

    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceByStudent(
            String universityId);

    List<Attendance> getAttendanceByEvent(
            Long eventId);
}