package de.ait.task_05.controllers;

import de.ait.task_05.dtos.EventDto;
import de.ait.task_05.dtos.NewEventDto;
import de.ait.task_05.dtos.NewParticipantDto;
import de.ait.task_05.dtos.ParticipantDto;
import de.ait.task_05.services.EventService;
import de.ait.task_05.services.ParticipantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tags(value = @Tag(name="Participants operations"))
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;
    @Operation(summary = "Adding new participant", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Participant was successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParticipantDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Request error")})
    @PostMapping("/register")
    public ResponseEntity<ParticipantDto> register(@RequestBody NewParticipantDto newParticipant) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(participantService.register(newParticipant));
    }

}
