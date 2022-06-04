package com.polytech.sportbook.service;

import com.polytech.sportbook.domain.Event;
import com.polytech.sportbook.repo.EventRepository;
import com.polytech.sportbook.repo.SportObjectRepository;
import org.checkerframework.checker.optional.qual.MaybePresent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    private EventService eventService;

    @BeforeEach
    void setup(){
        eventService = new EventServiceImpl(eventRepository, sportObjectRepository);
    }

    @Mock
    private EventRepository eventRepository;

    @Mock
    private SportObjectRepository sportObjectRepository;

    @Test
    public void saveEventTest(){
        Event event = new Event();
        eventService.saveEvent(event);
        Mockito.verify(eventRepository).save(event);
    }

    @Test
    public void getEventsTest(){
        eventService.events();
        Mockito.verify(eventRepository).findAll();
    }

    @Test
    public void getEventByIdTest(){
        Long id = 1L;
        eventService.getEventById(id);
        Mockito.verify(eventRepository).findById(id);
    }

    @Test
    public void updateEventTest(){
        Long id = 2L;
        Event event = new Event();
        event = eventService.updateEvent(id, event);
        Mockito.verify(eventRepository).findById(id);
        Mockito.verify(eventRepository).save(event);
    }

    @Test
    public void deleteEventTest(){
        Long id = 3L;
        eventService.deleteEvent(id);
        Mockito.verify(eventRepository).deleteById(id);
    }


    @Test
    public void assignEventToObjectTest(){
        Long obj_id = 1L;
        Long ev_id = 2L;
        eventService.assignEventToObject(obj_id, ev_id);
        Mockito.verify(eventRepository).findById(ev_id);
        Mockito.verify(sportObjectRepository).findById(obj_id);
    }
}
