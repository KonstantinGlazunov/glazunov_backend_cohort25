package services.impl;

import models.Event;
import repositories.EventsRepository;
import services.EventService;

import java.time.LocalDate;

public class EventServiceImpl implements EventService {

    private final EventsRepository eventRepository;

    public EventServiceImpl(EventsRepository userRepository) {
        this.eventRepository = userRepository;
    }


    public Event createEvent(String title, LocalDate startDate, LocalDate endDate) {
        if (title == null || title.equals("") || title.equals(" ")) {
            throw new IllegalArgumentException("Titel is required field");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End data can not be before start data");
        }
        Event event = new Event(title, startDate, endDate);
        eventRepository.save(event);
        return event;

    }

}
