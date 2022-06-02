package com.polytech.sportbook.controller;

import com.polytech.sportbook.domain.Event;
import com.polytech.sportbook.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EventController {
    private final EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents() {
        return ResponseEntity.ok().body(eventService.events());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok().body(eventService.getEventById(id));
    }

    @PostMapping("/events")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/events").toUriString());
        return ResponseEntity.created(uri).body(eventService.saveEvent(event));
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok().body(eventService.updateEvent(id, event));
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/events")
    public ResponseEntity<?> assignEventToObject(@RequestParam Long ev_id, @RequestParam Long obj_id) {
        eventService.assignEventToObject(obj_id, ev_id);
        return ResponseEntity.ok().build();
    }


}
