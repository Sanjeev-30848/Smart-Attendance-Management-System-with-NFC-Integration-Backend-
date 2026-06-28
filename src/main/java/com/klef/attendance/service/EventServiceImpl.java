package com.klef.attendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klef.attendance.dto.EventDTO;
import com.klef.attendance.entity.Event;
import com.klef.attendance.entity.EventStatus;
import com.klef.attendance.exception.ResourceNotFoundException;
import com.klef.attendance.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl
        implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event createEvent(
            EventDTO dto) {

        Event event = Event.builder()
                .eventName(dto.getEventName())
                .eventDescription(dto.getEventDescription())
                .eventDate(dto.getEventDate())
                .eventVenue(dto.getEventVenue())
                .silPointsAwarded(dto.getSilPointsAwarded())
                .eventStatus(dto.getEventStatus())
                .active(dto.getActive())
                .build();

        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Event not found"));
    }

    @Override
    public Event updateEvent(
            Long id,
            EventDTO dto) {

        Event event = getEventById(id);

        event.setEventName(dto.getEventName());
        event.setEventDescription(dto.getEventDescription());
        event.setEventDate(dto.getEventDate());
        event.setEventVenue(dto.getEventVenue());
        event.setSilPointsAwarded(
                dto.getSilPointsAwarded());
        event.setEventStatus(
                dto.getEventStatus());
        event.setActive(
                dto.getActive());

        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByActiveTrue();
    }

    @Override
    public List<Event> getInactiveEvents() {
        return eventRepository.findByActiveFalse();
    }

    @Override
    public List<Event> getEventsByStatus(
            EventStatus eventStatus) {

        return eventRepository
                .findByEventStatus(eventStatus);
    }
}