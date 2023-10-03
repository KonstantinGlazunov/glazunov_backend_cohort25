package de.ait.task_05.services.impl;

import de.ait.task_05.dtos.EventDto;
import de.ait.task_05.dtos.NewEventDto;
import de.ait.task_05.models.Event;
import de.ait.task_05.repositories.EventsRepository;
import de.ait.task_05.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Date.from;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventsRepository eventsRepository;

    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        Event event  = Event.builder()
                .title(newEvent.getTitle())
                .date(LocalDate.parse(newEvent.getDate()))
                .build();
        eventsRepository.save(event);

        return EventDto.from(event);
    }

    @Override
    public List<EventDto> getEvents() {
        return null;
    }

    @Override
    public EventDto getEvent(Long eventId) {
        return null;
    }

    @Override
    public EventDto addSiteToEvent(Long eventId, Long siteId) {
        return null;
    }
}
