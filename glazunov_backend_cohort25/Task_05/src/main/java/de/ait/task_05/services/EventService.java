package de.ait.task_05.services;

import de.ait.task_05.dtos.*;

import java.util.List;

public interface EventService {

    List<ParticipantDto> addParticipantToEvent(Long eventId, ParticipantToEventDto participantData);

    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getEvents();

    EventDto getEvent(Long eventId);

    EventDto addSiteToEvent(Long eventId, Long siteId);

    List<ParticipantDto> getParticipantsOfEvent(Long eventId);

    EventDto updateEvent(Long eventId, UpdateEventDto eventDataForUpdate);
}
