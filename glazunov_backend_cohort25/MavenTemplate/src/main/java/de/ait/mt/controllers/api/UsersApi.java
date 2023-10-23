package de.ait.mt.controllers.api;


import de.ait.mt.dto.NewUserDto;
import de.ait.mt.dto.StandardResponseDto;
import de.ait.mt.dto.UserDto;
import de.ait.mt.security.details.AuthenticatedUser;
import de.ait.mt.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Tags(
        @Tag(name = "Users")
)
@RequestMapping("/api/users")
public interface UsersApi {

 /*   @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    UsersPage getAllUsers(@RequestParam("page") int page, @RequestParam("size") int size,
                          @RequestParam("orderBy") String orderBy, @RequestParam(value = "desc", required = false) Boolean desc);
*/
    @Operation(summary = "User registration", description = "Available to all. The default role is USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "User registered",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "User with same email is exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    UserDto register(@RequestBody @Valid NewUserDto newUser);

    @GetMapping("/profile")
    UserDto getProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user);


}
