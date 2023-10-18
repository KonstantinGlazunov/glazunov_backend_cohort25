package de.ait.task_05.controllers.api;


import de.ait.task_05.dtos.UserDto;
import de.ait.task_05.dtos.UserToEventDto;
import de.ait.task_05.dtos.eventsDtos.EventDto;
import de.ait.task_05.dtos.eventsDtos.NewEventDto;
import de.ait.task_05.dtos.eventsDtos.UpdateEventDto;
import de.ait.task_05.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(value = @Tag(name = "Events operations"))
@RequestMapping("/api/events")

@ApiResponses(value = {
        @ApiResponse(responseCode = "401",
                description = "User unauthorized",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = EventDto.class))),
        @ApiResponse(responseCode = "403",
                description = "Forbid",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ValidationErrorsDto.class))
        )})

public interface EventsApi {

    @Operation(summary = "Adding new event", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Event was successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))
            )})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EventDto addEvent(@RequestBody NewEventDto newEvent);

    @Operation(summary = "Adding site to event", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    description = "Site was successfully added to event",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Request error")})

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{event-id}/sites/{site-id}")
    EventDto addSiteToEvent(@PathVariable("event-id") Long eventId,
                                            @PathVariable("site-id") Long siteId);


    @Operation(summary = "Adding participant to event", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202",
                    description = "Participant was successfully added to event",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Request error")})

    @PostMapping("/{event-id}/user")
    @ResponseStatus(HttpStatus.CREATED)
    List<UserDto> addParticipantToEvent(
            @PathVariable("event-id") Long eventId,
            @RequestBody UserToEventDto UserData);

    @Operation(summary = "Getting participants of event", description = "only admin available")
    @GetMapping("/{event-id}/participants")
    List<UserDto> getUserOfEvent(@PathVariable("event-id") Long eventId);

    @Operation(summary = "Update Event", description = "only admin available")
    @PutMapping("/{event-id}")
    EventDto updateEvent(@PathVariable("event-id") Long eventId,
                                         @RequestBody UpdateEventDto eventDataForUpdate);

}
