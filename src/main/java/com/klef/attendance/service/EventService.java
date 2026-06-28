package com.klef.attendance.service;

import java.util.List;

import com.klef.attendance.dto.EventDTO;
import com.klef.attendance.entity.Event;
import com.klef.attendance.entity.EventStatus;

public interface EventService {

    Event createEvent(EventDTO eventDTO);

    List<Event> getAllEvents();

    Event getEventById(Long id);

    Event updateEvent(Long id,
                      EventDTO eventDTO);

    void deleteEvent(Long id);

    List<Event> getActiveEvents();

    List<Event> getInactiveEvents();

    List<Event> getEventsByStatus(
            EventStatus eventStatus);
}