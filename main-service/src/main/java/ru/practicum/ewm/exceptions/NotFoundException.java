package ru.practicum.ewm.exceptions;

import lombok.Getter;
import ru.practicum.ewm.dto.api.ApiError;

public class NotFoundException extends RuntimeException {

    @Getter
    private final ApiError apiError;

    public NotFoundException(final String message) {
        super(message);
        apiError = new ApiError();
        apiError.setMessage(message);
        apiError.setReason("The required object was not found");
        apiError.setStatus("404 NOT_FOUND");
    }
}