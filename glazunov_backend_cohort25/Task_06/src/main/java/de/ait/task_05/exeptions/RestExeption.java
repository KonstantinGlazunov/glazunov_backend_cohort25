package de.ait.task_05.exeptions;

import org.springframework.http.HttpStatus;

public class RestExeption extends RuntimeException {
    private final HttpStatus status;

    public RestExeption(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
