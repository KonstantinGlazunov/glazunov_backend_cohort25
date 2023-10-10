package de.ait.task_05.controllers;

import de.ait.task_05.dtos.NewEventDto;
import de.ait.task_05.dtos.NewSiteDto;
import de.ait.task_05.dtos.SiteDto;
import de.ait.task_05.services.SitesService;
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
@Tags(value = @Tag(name = "Sites operations"))
@RequestMapping("/api/sites")
public class SitesController {

    private final SitesService sitesService;
    @Operation(summary = "Adding new site", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Site was successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SiteDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Request error")})
    @PostMapping
    public ResponseEntity<SiteDto> addSite(@RequestBody NewSiteDto newSite) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sitesService.addSite(newSite));
    }


}
