package com.klef.attendance.service;

import com.klef.attendance.dto.LoginRequest;
import com.klef.attendance.dto.LoginResponse;
import com.klef.attendance.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}