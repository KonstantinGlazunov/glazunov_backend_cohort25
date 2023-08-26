package de.ait.services.impl;

import de.ait.models.Event;
import de.ait.repositories.EventsRepository;
import de.ait.services.EventService;

import java.time.LocalDate;
import java.util.List;

public class EventServiceImpl implements EventService {

    private final EventsRepository eventRepository;

    public EventServiceImpl(EventsRepository userRepository) {

        this.eventRepository = userRepository;
    }


    public Event createEvent(String title, LocalDate startDate, LocalDate endDate) {
        if (title == null || title.equals("") || title.equals(" ")) {
            throw new IllegalArgumentException("Title is required field");
        }
        Event extendEvent =  eventRepository.findByTitle(title);
        if (extendEvent !=null){
            throw new IllegalArgumentException("There is already Event with same title");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End data can not be before start data");
        }

        Event event = new Event(title, startDate, endDate);
        eventRepository.save(event);
        return event;

    }

    @Override
    public List <Event> findAll () {
        return eventRepository.findAll();
    }


}
