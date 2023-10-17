package de.ait.task_05.services;

import de.ait.task_05.dtos.*;
import de.ait.task_05.dtos.eventsDtos.EventDto;
import de.ait.task_05.dtos.eventsDtos.NewEventDto;
import de.ait.task_05.dtos.eventsDtos.UpdateEventDto;

import java.util.List;

public interface EventService {

    List<UserDto> addParticipantToEvent(Long eventId, UserToEventDto participantData);

    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getEvents();

    EventDto getEvent(Long eventId);

    EventDto addSiteToEvent(Long eventId, Long siteId);

    List<UserDto> getParticipantsOfEvent(Long eventId);

    EventDto updateEvent(Long eventId, UpdateEventDto eventDataForUpdate);
}
