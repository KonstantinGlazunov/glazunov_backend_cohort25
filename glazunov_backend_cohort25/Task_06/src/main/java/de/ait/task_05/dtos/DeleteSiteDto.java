package de.ait.task_05.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DeleteSiteDto {
    @NotNull
    @NotBlank
    @NotEmpty
    @Schema(description = "Sites name", example = "Music hall")
    private Long id;

}
