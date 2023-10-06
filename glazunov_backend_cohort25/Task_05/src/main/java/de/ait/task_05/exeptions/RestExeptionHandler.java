package de.ait.task_05.exeptions;

import de.ait.task_05.dtos.StandardResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExeptionHandler {
    @ExceptionHandler(value = RestExeption.class)
    public ResponseEntity<StandardResponseDto> handleRestException(RestExeption e) {
return ResponseEntity
        .status(e.getStatus())
        .body(StandardResponseDto.builder()
                .message(e.getMessage())
                .build());
    }

}
