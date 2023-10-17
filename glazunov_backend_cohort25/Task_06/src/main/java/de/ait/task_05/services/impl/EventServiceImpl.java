package de.ait.task_05.services.impl;

import de.ait.task_05.dtos.*;
import de.ait.task_05.dtos.eventsDtos.EventDto;
import de.ait.task_05.dtos.eventsDtos.NewEventDto;
import de.ait.task_05.dtos.eventsDtos.UpdateEventDto;
import de.ait.task_05.exeptions.RestExeption;
import de.ait.task_05.models.Event;
import de.ait.task_05.models.User;
import de.ait.task_05.repositories.EventsRepository;
import de.ait.task_05.repositories.UsersRepository;
import de.ait.task_05.repositories.SitesRepository;
import de.ait.task_05.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventsRepository eventsRepository;
    private final SitesRepository sitesRepository;
    private final UsersRepository usersRepository;


    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        Event event = Event.builder()
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
        getEventOrThrow(eventId);
        sitesRepository.findById(siteId)
                .orElseThrow(() -> new RestExeption(HttpStatus.NOT_FOUND, "Site with id <" + siteId + "> not found"));
        eventsRepository.upDateSiteId(eventId, siteId);
        return EventDto.from(eventsRepository.findById(eventId).get());
    }


    @Override
    public List<UserDto> addParticipantToEvent(Long eventId, UserToEventDto participantData) {
        Event event = getEventOrThrow(eventId);
        User user = usersRepository.findById(participantData.getUserId())
                .orElseThrow(() -> new RestExeption(HttpStatus.NOT_FOUND,
                        "Participant with id: <" + participantData.getUserId() + "> not found"));
        boolean isParticipantAlreadyOnEvent = user.getEvents().add(event);
        if (!isParticipantAlreadyOnEvent) {
            throw new RestExeption(HttpStatus.BAD_REQUEST, "Participant with id <" + participantData.getUserId() +
                    "> already in Event <" + event.getId() + ">");
        }
        usersRepository.save(user);
        Set<User> participantsOfEvent = usersRepository.findAllByEventsContainsOrderById(event);
        return UserDto.from(participantsOfEvent);
    }

    private Event getEventOrThrow(Long eventId) {
        return eventsRepository.findById(eventId)
                .orElseThrow(() -> new RestExeption(HttpStatus.NOT_FOUND, "Event with id <" + eventId + "> not found"));
    }


    @Override
    public List<UserDto> getParticipantsOfEvent(Long eventId) {
        Event event = getEventOrThrow(eventId);
        Set<User> participantsOfEvent = usersRepository.findAllByEventsContainsOrderById(event);
        return UserDto.from(participantsOfEvent);
    }

    @Override
    public EventDto updateEvent(Long eventId, UpdateEventDto eventDataForUpdate) {
        Event event = getEventOrThrow(eventId);
        if (eventDataForUpdate.getTitle() != null) {
            event.setTitle(eventDataForUpdate.getTitle());
        } else {
            eventDataForUpdate.setTitle(null);
        }
        if (eventDataForUpdate.getData() != null) {
            event.setDate(LocalDate.parse(eventDataForUpdate.getData()));
        } else {
            event.setDate(null);
        }
        if (eventDataForUpdate.getSite() != null) {
            event.setSite(sitesRepository.getSiteById(eventDataForUpdate.getSite()));
        } else {
            event.setSite(null);
        }
        eventsRepository.save(event);
        return EventDto.from(event);
    }


}


