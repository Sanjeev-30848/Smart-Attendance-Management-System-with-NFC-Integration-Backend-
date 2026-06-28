package com.klef.attendance.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.attendance.dto.EventDTO;
import com.klef.attendance.entity.Event;
import com.klef.attendance.entity.EventStatus;
import com.klef.attendance.service.EventService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Event>
            createEvent(
                    @RequestBody EventDTO dto) {

        return ResponseEntity.ok(
                eventService.createEvent(dto));
    }

    @GetMapping
    public ResponseEntity<List<Event>>
            getAllEvents() {

        return ResponseEntity.ok(
                eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event>
            getEventById(
                    @PathVariable Long id) {

        return ResponseEntity.ok(
                eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event>
            updateEvent(
                    @PathVariable Long id,
                    @RequestBody EventDTO dto) {

        return ResponseEntity.ok(
                eventService.updateEvent(
                        id,
                        dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
            deleteEvent(
                    @PathVariable Long id) {

        eventService.deleteEvent(id);

        return ResponseEntity.ok(
                "Event deleted successfully");
    }

    @GetMapping("/active")
    public ResponseEntity<List<Event>>
            getActiveEvents() {

        return ResponseEntity.ok(
                eventService.getActiveEvents());
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<Event>>
            getInactiveEvents() {

        return ResponseEntity.ok(
                eventService.getInactiveEvents());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Event>>
            getByStatus(
                    @PathVariable EventStatus status) {

        return ResponseEntity.ok(
                eventService
                        .getEventsByStatus(
                                status));
    }
}