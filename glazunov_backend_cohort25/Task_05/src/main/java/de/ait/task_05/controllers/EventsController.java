package de.ait.task_05.controllers;

import de.ait.task_05.dtos.EventDto;
import de.ait.task_05.dtos.NewEventDto;
import de.ait.task_05.models.Event;
import de.ait.task_05.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Tags(value = @Tag(name = "Events operations"))
@RequestMapping("/api/events")
public class EventsController {

    private final EventService eventService;
    @Operation(summary = "Adding new event", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Event was successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Request error")})
    @PostMapping()
    public ResponseEntity<EventDto> addEvent(@RequestBody NewEventDto newEvent) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventService.addEvent(newEvent));
    }


}
