package de.ait.task_05.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "New participant", description = "Registration data")
public class NewParticipantDto {

    @NotNull
    @Schema(description = "participant name", example = "Jon")
    private String name;

    @Email
    @NotNull
    @Schema(description = "Participant Email", example = "user@mail.com")
    private String email;

}