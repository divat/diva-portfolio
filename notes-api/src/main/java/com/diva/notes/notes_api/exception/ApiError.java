package com.diva.notes.notes_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String path;
    private Map<String, String> details;

    public ApiError() {}

    public ApiError(int status, String error, String path, Map<String, String> details) {
        this.status = status;
        this.error = error;
        this.path = path;
        this.details = details;
    }

}
