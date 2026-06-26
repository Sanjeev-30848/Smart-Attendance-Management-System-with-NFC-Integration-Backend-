package com.klef.attendance.entity;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.attendance.dto.EventDTO;
import com.klef.attendance.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EventController {

    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDTO addEvent(@RequestBody EventDTO eventDTO) {
        return eventService.addEvent(eventDTO);
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDTO getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/active")
    public List<EventDTO> getActiveEvents() {
        return eventService.getActiveEvents();
    }

    @PutMapping("/{id}")
    public EventDTO updateEvent(
            @PathVariable Long id,
            @RequestBody EventDTO eventDTO) {

        return eventService.updateEvent(id, eventDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {

        eventService.deleteEvent(id);

        return "Event Deleted Successfully";
    }
}