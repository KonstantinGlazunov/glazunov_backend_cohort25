package de.ait.task_05.controllers;

import de.ait.task_05.dtos.*;
import de.ait.task_05.dtos.eventsDtos.EventDto;
import de.ait.task_05.dtos.eventsDtos.NewEventDto;
import de.ait.task_05.dtos.eventsDtos.UpdateEventDto;
import de.ait.task_05.services.EventService;
import de.ait.task_05.services.ParticipantService;
import de.ait.task_05.validation.dto.ValidationErrorsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Tags(value = @Tag(name = "Events operations"))
@RequestMapping("/api/events")
public class EventsController {

    private final EventService eventService;
    private final ParticipantService participantService;

    @Operation(summary = "Adding new event", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Event was successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))})
    @PostMapping
    public ResponseEntity<EventDto> addEvent(@RequestBody @Valid NewEventDto newEvent) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventService.addEvent(newEvent));
    }

    @Operation(summary = "Adding site to event", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    description = "Site was successfully added to event",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Request error")})

    @PostMapping("/{event-id}/sites/{site-id}")
    public ResponseEntity<EventDto> addSiteToEvent(@PathVariable ("event-id") Long eventId,
                                                   @PathVariable("site-id") Long siteId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(eventService.addSiteToEvent(eventId, siteId));
    }


    @Operation(summary = "Adding participant to event", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    description = "Participant was successfully added to event",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Request error")})

    @PostMapping("/{event-id}/participant")
    public ResponseEntity<List<ParticipantDto>> addParticipantToEvent(
            @PathVariable("event-id") Long eventId,
            @RequestBody @Valid ParticipantToEventDto participantData) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventService.addParticipantToEvent(eventId, participantData));
    }

    @Operation(summary = "Adding participants of event", description = "only admin available")
    @GetMapping("/{event-id}/participants")
    public ResponseEntity<List<ParticipantDto>> getParticipantsOfEvent(@PathVariable("event-id") Long eventId){
        return ResponseEntity.ok(eventService.getParticipantsOfEvent(eventId));
    }

    @Operation(summary = "Update Event", description = "only admin available")
    @PutMapping("/{event-id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable("event-id") Long eventId,
                                                @RequestBody UpdateEventDto eventDataForUpdate) {
        return ResponseEntity
                .ok(eventService.updateEvent(eventId, eventDataForUpdate));
    }



}
