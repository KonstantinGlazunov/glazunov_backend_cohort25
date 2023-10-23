package de.ait.mt.validation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ValidationError", description = "Validation error description")
public class ValidationErrorDto {

    @Schema(description = "field`s name in which the error occurred", example = "price")
    private String field;
    @Schema(description = "value entered by user and rejected by the server", example = "1000.0")
    private String rejectedValue;
    @Schema(description = "the message we need to show to the user", example = "must be less than or equal to 200")
    private String message;
}
