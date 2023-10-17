package de.ait.task_05.dtos.eventsDtos;

import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.persistence.Column;
//import jakarta.persistence.ManyToMany;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class NewEventDto {
    @NotNull
    @NotBlank
    @NotEmpty
    @Schema(description = "Event title", example = "Concert")
    private String title;

    @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
    @Schema(description = "Date of event start", example = "2023-12-01")
    private String date;

 //   private String site;
}
