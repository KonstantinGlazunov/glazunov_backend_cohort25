package de.ait.mt.validation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ValidationErrors", description = "validation errors info")
public class ValidationErrorsDto {

    @Schema(description = "validation errors list")
    private List<ValidationErrorDto> errors;
}
