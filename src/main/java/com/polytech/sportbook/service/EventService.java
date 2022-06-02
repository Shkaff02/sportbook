package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Event;

import java.util.List;

public interface EventService {
    Event saveEvent(Event event);

    List<Event> events();

    Event getEventById(Long id);

    Event updateEvent(Long id, Event event);

    void deleteEvent(Long id);

    void assignEventToObject(Long obj_id, Long ev_id);
}
