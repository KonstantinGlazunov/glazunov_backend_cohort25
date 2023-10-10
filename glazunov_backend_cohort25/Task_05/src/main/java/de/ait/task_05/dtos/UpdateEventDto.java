package de.ait.task_05.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Schema(description = "Fields for update, null-values saved in Data base")
public class UpdateEventDto {

    @NotBlank
    @NotEmpty
    private String title;
    @NotBlank
    @NotEmpty
    private String data;
    @NotBlank
    @NotEmpty
    private Long site;
}
