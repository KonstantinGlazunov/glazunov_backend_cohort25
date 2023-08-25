package planer.repositories;

import planer.models.Event;

import java.time.LocalDate;
import java.util.List;

public class EventRepositoryListImpl implements EventRepository {
    @Override
    public Event findBiId(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public void save(Event model) {

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
