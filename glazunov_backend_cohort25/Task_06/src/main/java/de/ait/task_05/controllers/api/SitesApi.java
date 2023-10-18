package de.ait.task_05.controllers.api;

import de.ait.task_05.dtos.NewSiteDto;
import de.ait.task_05.dtos.SiteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tags(value = @Tag(name = "Sites operations"))
@RequestMapping("/api/sites")
public interface SitesApi {

    @Operation(summary = "Adding new site", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Site was successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SiteDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Request error")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SiteDto addSite(@RequestBody NewSiteDto newSite);

    @Operation(summary = "Delete Site from Event and from Sites ", description = "only admin available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Site was successfully deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SiteDto.class))),

            @ApiResponse(responseCode = "400",
                    description = "Request error"),
            @ApiResponse(responseCode = "404",
                    description = "Site not found")})

    @DeleteMapping("/{site-id}")
    public SiteDto deleteSite(@PathVariable("site-id") Long siteId);

}
