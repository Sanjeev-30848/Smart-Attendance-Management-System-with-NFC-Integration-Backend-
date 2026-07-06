package com.klef.attendance.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.klef.attendance.entity.Attendance;
import com.klef.attendance.service.AttendanceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/{universityId}/{eventId}")
    public ResponseEntity<Attendance>
            markAttendance(
                    @PathVariable String universityId,
                    @PathVariable Long eventId) {

        return ResponseEntity.ok(
                attendanceService
                        .markAttendance(
                                universityId,
                                eventId));
    }

    @GetMapping
    public ResponseEntity<List<Attendance>>
            getAllAttendance() {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance());
    }

    @GetMapping("/student/{universityId}")
    public ResponseEntity<List<Attendance>>
            getByStudent(
                    @PathVariable String universityId) {

        return ResponseEntity.ok(
                attendanceService
                        .getAttendanceByStudent(
                                universityId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Attendance>>
            getByEvent(
                    @PathVariable Long eventId) {

        return ResponseEntity.ok(
                attendanceService
                        .getAttendanceByEvent(
                                eventId));
    }
}