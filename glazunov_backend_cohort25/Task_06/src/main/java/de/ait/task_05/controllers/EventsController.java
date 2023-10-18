package de.ait.task_05.controllers;

import de.ait.task_05.controllers.api.EventsApi;
import de.ait.task_05.dtos.*;
import de.ait.task_05.dtos.eventsDtos.EventDto;
import de.ait.task_05.dtos.eventsDtos.NewEventDto;
import de.ait.task_05.dtos.eventsDtos.UpdateEventDto;
import de.ait.task_05.services.EventService;
import de.ait.task_05.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RequiredArgsConstructor
@RestController

public class EventsController implements EventsApi {

    private final EventService eventService;
    private final UserService userService;

    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        return eventService.addEvent(newEvent);
    }

    @Override
    public EventDto addSiteToEvent(Long eventId,
                                                   Long siteId) {
        return eventService.addSiteToEvent(eventId, siteId);
    }


    @Override
    public List<UserDto> addParticipantToEvent(
            Long eventId,
            UserToEventDto UserData) {
        return eventService.addParticipantToEvent(eventId, UserData);
    }

    @Override
    public List<UserDto> getUserOfEvent(Long eventId) {
        return eventService.getParticipantsOfEvent(eventId);
    }

    @Override
    public EventDto updateEvent(Long eventId,
                                                UpdateEventDto eventDataForUpdate) {
        return eventService.updateEvent(eventId, eventDataForUpdate);
    }


}
