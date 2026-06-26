package com.klef.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.attendance.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByEventStatus(String eventStatus);
}