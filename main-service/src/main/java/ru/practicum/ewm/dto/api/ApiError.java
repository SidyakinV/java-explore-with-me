package ru.practicum.ewm.dto.api;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiError {

    private List<String> errors;
    private String message;
    private String reason;
    private String status;
    private LocalDateTime timestamp = LocalDateTime.now();

}
