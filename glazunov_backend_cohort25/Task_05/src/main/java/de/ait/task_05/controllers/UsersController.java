package de.ait.task_05.controllers;

import de.ait.task_05.dtos.NewUsertDto;
import de.ait.task_05.dtos.StandardResponseDto;
import de.ait.task_05.dtos.UserDto;
import de.ait.task_05.security.details.AuthenticatedUser;
import de.ait.task_05.services.UserService;
import de.ait.task_05.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequiredArgsConstructor
@RestController
@Tags(value = @Tag(name="Participants operations"))
@RequestMapping("/api/users")
public class UsersController {

    private final UserService usersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity
                .ok(usersService.getAllUsers());
    }

    @Operation(summary = "Adding new participant", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Participant was successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "User with same email already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))),

    })
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody NewUsertDto newParticipant) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usersService.register(newParticipant));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser  user) {
        Long currentUserId = user.getId();
        return ResponseEntity
                .ok((UserDto) usersService.getUserById(currentUserId));      //ЗДЕСЬ появилась ошибка!!!!!!!!
    }


}
