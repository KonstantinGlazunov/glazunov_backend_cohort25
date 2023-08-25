package planer.services;

import planer.models.Event;
import planer.repositories.EventRepository;

import java.time.LocalDate;

public class EventsService {

    private final EventRepository eventRepository;

    public EventsService(EventRepository userRepository) {
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
