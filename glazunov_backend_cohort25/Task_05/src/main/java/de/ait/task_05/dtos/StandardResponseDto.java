package de.ait.task_05.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Message", description = "Any massage from server")
public class StandardResponseDto {

    @Schema(description = "Possible error message, change of standing position, etc.", example = "Event not found")
    public String message;


}
