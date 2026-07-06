package com.klef.attendance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.attendance.entity.User;

public interface UserRepository extends JpaRepository<User, Long> 
{

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}