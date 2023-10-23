package de.ait.mt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Message", description = "Eny message from server")
public class StandardResponseDto {
    @Schema(description = "Possible: error message, status change, etc.", example = "Not found")
    private String message;
}
