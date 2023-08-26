package de.ait.repositories.impl;

import de.ait.models.Event;
import de.ait.repositories.EventsRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryListImpl implements EventsRepository {
    private final List<Event> events = new ArrayList<>();
    private Long generatedId = 1L;

    @Override
    public Event findBiId(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        return events;
    }

    @Override
    public void save(Event event) {
        events.add(event);
        event.setId(generatedId);
        generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void upDate(Event model) {

    }

    @Override
    public Event findByTitle(String title) {
      /*  for (Event event : events ){
            if (event.getTitle().equals(title)){
                return event;
            }
        }
        return null; */
        return events.stream()
                .filter(event -> event.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Event findByStartDate(LocalDate startDate) {
        return null;
    }
}
