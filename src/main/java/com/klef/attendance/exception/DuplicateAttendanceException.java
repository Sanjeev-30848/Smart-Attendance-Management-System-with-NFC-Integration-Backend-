package com.klef.attendance.exception;

public class DuplicateAttendanceException
        extends RuntimeException {

    public DuplicateAttendanceException(String message) {
        super(message);
    }
}