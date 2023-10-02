package impl;

import models.Event;
import repositories.EventsRepository;

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
        return null;
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
    public Event findByTitle(String titel) {
        return null;
    }

    @Override
    public Event findByStartDate(LocalDate startDate) {
        return null;
    }
}
