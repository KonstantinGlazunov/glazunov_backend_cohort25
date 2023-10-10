package de.ait.task_05.services;

import de.ait.task_05.dtos.EventDto;
import de.ait.task_05.dtos.NewEventDto;
import de.ait.task_05.dtos.ParticipantDto;
import de.ait.task_05.dtos.ParticipantToEventDto;

import java.util.List;

public interface EventService {

    List<ParticipantDto> dddParticipantToEvent(Long eventId, ParticipantToEventDto participantData);

    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getEvents();

    EventDto getEvent(Long eventId);

    EventDto addSiteToEvent(Long eventId, Long siteId);
}
