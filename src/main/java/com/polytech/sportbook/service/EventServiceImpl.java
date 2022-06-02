package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Event;
import com.polytech.sportbook.domain.SportObject;
import com.polytech.sportbook.repo.EventRepository;
import com.polytech.sportbook.repo.SportObjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final SportObjectRepository sportObjectRepository;


    @Override
    public Event saveEvent(Event event) {
        log.info("Saving event {} to database", event.getName());
        return eventRepository.save(event);
    }

    @Override
    public List<Event> events() {
        log.info("Fetching all events from database");
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long id) {
        log.info("Fetching event by id {}", id);
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        log.info("Updating event with id {}", id);
        Event updated = eventRepository.findById(id).orElse(null);
        if (updated != null) {
            updated.setName(event.getName());
            updated.setDescription(event.getDescription());
            updated.setDateTime(event.getDateTime());
            updated.setSportObject(event.getSportObject());
        }
        return eventRepository.save(updated);
    }

    @Override
    public void deleteEvent(Long id) {
        log.info("Deleting event with id {}", id);
        eventRepository.deleteById(id);
    }

    @Override
    public void assignEventToObject(Long obj_id, Long ev_id) {
        log.info("Assigning event with id {} to object with id {}", ev_id, obj_id);
        Event event = eventRepository.findById(ev_id).orElse(null);
        SportObject sportObject = sportObjectRepository.findById(obj_id).orElse(null);
        if (event != null) {
            event.setSportObject(sportObject);
            eventRepository.save(event);
        }
    }
}
