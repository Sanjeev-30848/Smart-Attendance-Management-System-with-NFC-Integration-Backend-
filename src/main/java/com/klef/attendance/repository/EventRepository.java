package com.klef.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.attendance.entity.Event;
import com.klef.attendance.entity.EventStatus;

@Repository
public interface EventRepository
        extends JpaRepository<Event, Long> {

    List<Event> findByActiveTrue();

    List<Event> findByActiveFalse();

    List<Event> findByEventStatus(EventStatus eventStatus);
}